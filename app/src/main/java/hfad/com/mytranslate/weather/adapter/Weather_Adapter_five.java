package hfad.com.mytranslate.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hfad.com.mytranslate.R;
import hfad.com.mytranslate.weather.Common.Common;
import hfad.com.mytranslate.weather.Models.Five_Days.Weather_5_Result;

public class Weather_Adapter_five extends RecyclerView.Adapter<Weather_Adapter_five.MyViewHolder> {

    Context context;
    Weather_5_Result weather_5_result;

    public Weather_Adapter_five(Context context, Weather_5_Result weather_5_result) {
        this.context = context;
        this.weather_5_result = weather_5_result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_weather_5_days,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //loda icon
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weather_5_result.list.get(i).weather.get(0).getIcon())
                .append(".png").toString()).into(myViewHolder.img_weather);
        myViewHolder.txt_data.setText(new StringBuilder(Common.convertUnixDate(weather_5_result.list.get(i).dt)));
        myViewHolder.txt_description.setText(new StringBuilder(weather_5_result.list.get(i).weather.get(0).getDescription()));
        myViewHolder.txt_temperature.setText(new StringBuilder(String.valueOf(weather_5_result.list.get(i).main.getTemp())).append(" °C "));

    }

    @Override
    public int getItemCount() {
        return weather_5_result.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_data, txt_description, txt_temperature;
        ImageView img_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_weather = (ImageView)itemView.findViewById(R.id.img_weather);
            txt_data = (TextView)itemView.findViewById(R.id.txt_data);
            txt_description = (TextView)itemView.findViewById(R.id.txt_description);
            txt_temperature = (TextView)itemView.findViewById(R.id.txt_temperature);
        }
    }
}
