package ir.airport.testweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyWeatherDetailViewAdapter extends RecyclerView.Adapter<MyWeatherDetailViewAdapter.ViewHolder> {
    private List<DetailWeather> mData;
    private LayoutInflater mInflater;
    private MyWeatherViewAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    MyWeatherDetailViewAdapter(Context context, List<DetailWeather> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public MyWeatherDetailViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_next_days, parent, false);
        return new MyWeatherDetailViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MyWeatherDetailViewAdapter.ViewHolder holder, int position) {

        String MPoster = mData.get(position).txttemp;
        holder.txtTemp.setText(MPoster);
        String Mdayofweek = mData.get(position).dayofweek;
        holder.txtDay.setText(Mdayofweek);
        String Mtxtdes = mData.get(position).txtdes;
        holder.txtdes.setText(Mtxtdes);

        switch (mData.get(position).imgShow)
        {
            case "01d":
                holder.imgShow.setImageResource(R.drawable.one);
                break;
            case "02d":
                holder.imgShow.setImageResource(R.drawable.two);
                break;
            case "03d":
                holder.imgShow.setImageResource(R.drawable.three);
                break;
            case "04d":
                holder.imgShow.setImageResource(R.drawable.four);
                break;
            case "09d":
                holder.imgShow.setImageResource(R.drawable.nine);
                break;
            case "10d":
                holder.imgShow.setImageResource(R.drawable.ten);
                break;
            case "11d":
                holder.imgShow.setImageResource(R.drawable.eleven);
                break;
            case "13d":
                holder.imgShow.setImageResource(R.drawable.thrtin);
                break;
            default:
                holder.imgShow.setImageResource(R.drawable.one);
                break;
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {

        return mData.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTemp,txtDay,txtdes;
        ImageView imgShow;
        ViewHolder(View itemView) {
            super(itemView);

            txtdes = itemView.findViewById(R.id.txtdes);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtTemp = itemView.findViewById(R.id.txttemp);
            imgShow = itemView.findViewById(R.id.imgShow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).txtdes;
    }

    // allows clicks events to be caught
    void setClickListener(MyWeatherViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
