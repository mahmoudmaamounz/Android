package com.weatherapp.lists;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androstock.myweatherapp.R;
import com.weatherapp.model.Weather;
import com.weatherapp.model.Weather;

import java.util.ArrayList;


public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.ViewHolder> {
    Context mContext;
    public ArrayList<Weather> weatherObjArray;
   static Typeface weatherFont;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView cityField, detailsField, tempMaxField, tempMinField, humidity_field, pressure_field, updatedField;
ImageView weatherIcon;
        public ViewHolder(View v) {
            super(v);
            updatedField = (TextView) v.findViewById(R.id.date_field);
            detailsField = (TextView) v.findViewById(R.id.summary_field);
            tempMaxField = (TextView) v.findViewById(R.id.temp_max_field);
            tempMinField = (TextView) v.findViewById(R.id.temp_min_field);
            humidity_field = (TextView) v.findViewById(R.id.humidity_field);
            pressure_field = (TextView) v.findViewById(R.id.pressure_field);
            weatherIcon = (ImageView) v.findViewById(R.id.weather_icon);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CityWeatherAdapter(Context context, ArrayList<Weather> weatherObjArray) {
        mContext = context;
        this.weatherObjArray = weatherObjArray;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public CityWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_forcast, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.updatedField.setText(weatherObjArray.get(position).getUpdateOn());
        holder.detailsField.setText(weatherObjArray.get(position).getDescription());
        holder.tempMaxField.setText(weatherObjArray.get(position).getMax_temp());
        holder.tempMinField.setText(weatherObjArray.get(position).getMin_temp());
        holder.humidity_field.setText(weatherObjArray.get(position).getHumidity());
        holder.pressure_field.setText(weatherObjArray.get(position).getPressure());
        holder.weatherIcon.setBackground(weatherObjArray.get(position).getIcon());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return weatherObjArray.size();
    }

    public void clear() {
        weatherObjArray.clear();
        notifyDataSetChanged();
    }

}