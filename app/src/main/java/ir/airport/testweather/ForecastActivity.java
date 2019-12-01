package ir.airport.testweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class ForecastActivity extends AppCompatActivity {
TextView txtDay,txtTemp;
ImageView imgShow;
    ArrayList<DetailWeather> mData;
    int dayOfWeek;
    MyWeatherDetailViewAdapter adapterLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        AsyncHttpClient client = new AsyncHttpClient();
        Calendar calendar = Calendar.getInstance();
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        txtDay=findViewById(R.id.txtDay);
        txtTemp=findViewById(R.id.txttemp);
        imgShow=findViewById(R.id.imgShow);
        Intent intent=getIntent();
        String City = intent.getStringExtra("City");
        String Url = "https://api.openweathermap.org/data/2.5/forecast/daily?units=metric&q="+City+"&lang=fa&appid=141a44d6c157c2a60e5e70551c399aba";// + intent.getStringExtra("ItemID");
        Log.d("svtest", Url);
        AsyncHttpClient myclient = new AsyncHttpClient();
        client.get(Url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("svtest1", String.valueOf(throwable));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                JSONObject jsonobject = null;
                try {
                    jsonobject = new JSONObject(responseString);
                    JSONArray jsonoArray = new JSONArray(jsonobject.getString("list"));
                    int dayof = dayOfWeek;
                    mData = new ArrayList<>();
                    for (int i = 0; i < jsonoArray.length(); i++) {
                        JSONObject jsonSub = jsonoArray.getJSONObject(i);
                        String temp=jsonSub.getString("temp");
                        JSONObject day=new JSONObject(temp);
                        Log.d("svtest2", day.getString("day"));

                        JSONArray jweatherArray = new JSONArray(jsonSub.getString("weather"));
                        JSONObject jsonWSub = jweatherArray.getJSONObject(0);


                        //mData.add(jsonWSub.getString("description"));
                        if (i==0)
                        {
                            mData.add(new DetailWeather("امروز",jsonWSub.getString("icon"),jsonWSub.getString("description"), day.getString("day")+"°C"));
                        }
                        else if (i == 1)
                        {
                            mData.add(new DetailWeather("فردا",jsonWSub.getString("icon"), jsonWSub.getString("description"), day.getString("day")+"°C"));
                        }
                        else if (i !=  0 && i !=  1)
                        {

                            mData.add(new DetailWeather(GetCurrentday(dayof), jsonWSub.getString("icon"), jsonWSub.getString("description"), day.getString("day")+"°C"));
                        }



                        if (dayof != 7)
                        {
                            dayof = dayof + 1;
                        }
                        else
                        {
                            dayof = 1;
                        }





                    }
                    RecyclerView recyclerView = findViewById(R.id.svshowitem);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ForecastActivity.this));
                    adapterLeft = new MyWeatherDetailViewAdapter(ForecastActivity.this,mData);
                    //adapterLeft.setClickListener(MainActivity.this);
                    recyclerView.setAdapter(adapterLeft);



                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        });


    }

    public String  GetCurrentday(int dayOfW)
    {
        String weekDay = "";



        if (Calendar.MONDAY == dayOfW) {
            weekDay = "دوشنبه";
        } else if (Calendar.TUESDAY == dayOfW) {
            weekDay = "سه شنبه";
        } else if (Calendar.WEDNESDAY == dayOfW) {
            weekDay = "چهارشنبه";
        } else if (Calendar.THURSDAY == dayOfW) {
            weekDay = "پنجشنبه";
        } else if (Calendar.FRIDAY == dayOfW) {
            weekDay = "جمعه";
        } else if (Calendar.SATURDAY == dayOfW) {
            weekDay = "شنبه";
        } else if (Calendar.SUNDAY == dayOfW) {
            weekDay = "یکشنبه";
        }
        return  weekDay;
    }
}





