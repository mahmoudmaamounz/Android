package com.weatherapp.lists;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androstock.myweatherapp.R;

import java.util.ArrayList;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder> {
    public ArrayList<String> citiesArrayList;
    public ArrayList<String> SelectedcitiesArrayList;
    Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cityTextView;
        public TextView countryTextView;
        public LinearLayout ll_listitem;

        public ViewHolder(View v) {
            super(v);
            cityTextView = (TextView) v.findViewById(R.id.list_city_textview);
            countryTextView = (TextView) v.findViewById(R.id.list_country_textview);
            ll_listitem=(LinearLayout)v.findViewById(R.id.city_list_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CitiesListAdapter(Context context, ArrayList<String> citiesArrayList, ArrayList<String> SelectedcitiesArrayList) {
        this.citiesArrayList = citiesArrayList;
        this.SelectedcitiesArrayList = SelectedcitiesArrayList;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CitiesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_list_layout, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String [] s= citiesArrayList.get(position).split(",");
        holder.cityTextView.setText(s[0]);
        if(s.length>2){
            holder.countryTextView.setText(s[2]);
        }else{
            if(s.length>1) {
                holder.countryTextView.setText(s[1]);
            }else{
                holder.countryTextView.setText(s[0]);
            }
        }
        if(SelectedcitiesArrayList.contains(citiesArrayList.get(position)))
            holder.ll_listitem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state));
        else
            holder.ll_listitem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_normal_state));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return citiesArrayList.size();
    }
}