package ir.airport.testweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;

public class CityListActivity extends AppCompatActivity implements MyWeatherViewAdapter.ItemClickListener {
    ArrayList<String> CityArry =new ArrayList<>();
    MyWeatherViewAdapter adapterLeft;
    com.arlib.floatingsearchview.FloatingSearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        mSearchView =findViewById(R.id.float_search);




        final SQLite helper = new SQLite(CityListActivity.this, "Sematec", null, 1);


        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {

                int CountTest = helper.CheckItemifSaved(currentQuery);
                if (CountTest == 0) {
                    helper.insertMovies(currentQuery);
                }
                Intent intent = new Intent(CityListActivity.this, MainActivity.class);
                intent.putExtra("City", currentQuery);
                startActivity(intent);
                LoadItems(helper);
            }
        });


        LoadItems(helper);



    }
    public void LoadItems(SQLite helper)
    {
        CityArry = helper.getAllStudentsName();
        String S = String.valueOf(CityArry.size());


        RecyclerView recyclerView = findViewById(R.id.rCity);
        recyclerView.setLayoutManager(new LinearLayoutManager(CityListActivity.this));
        adapterLeft = new MyWeatherViewAdapter(CityListActivity.this, CityArry);
        adapterLeft.setClickListener(CityListActivity.this);
        recyclerView.setAdapter(adapterLeft);
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(CityListActivity.this, MainActivity.class);
        intent.putExtra("City", CityArry.get(position));
        startActivity(intent);
    }
}
