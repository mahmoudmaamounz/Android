package com.weatherapp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androstock.myweatherapp.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.weatherapp.lists.CitiesListAdapter;
import com.weatherapp.utils.RecyclerItemClickListener;

import android.support.v7.view.ActionMode;

import static com.weatherapp.utils.WeatherApplication.wApp;

public class ListActivity extends AppCompatActivity {
    RecyclerView citiesRecyclerView;
    LinearLayoutManager mLayoutManager;
    Set<String> citiesList = new TreeSet<String>();
    private ActionMode mActionMode;
    Menu context_menu;
    boolean isMultiSelect = false;
    ArrayList<String> citiesArrayList = new ArrayList<String>();
    ArrayList<String> selectedcitiesArrayList = new ArrayList<String>();
    final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    final String TAG = "";
    CitiesListAdapter adapter;
    private ActionMode.Callback mActionModeCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_list);
        initiateSet();
        initiateRecyclerView();
        initiateAdapter();
        initiateFabButton();
    }

    public void initiateRecyclerView() {
        citiesRecyclerView = (RecyclerView) findViewById(R.id.city_list);
        citiesRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        citiesRecyclerView.setLayoutManager(mLayoutManager);
        mActionModeCallback = new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.list_menu, menu);
                context_menu = menu;
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; // Return false if nothing is done
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        HashMap<String, ArrayList<Double>> map = wApp.PrefgetHashMap();
                        for (int i = 0; i < selectedcitiesArrayList.size(); i++) {
                            citiesArrayList.remove(selectedcitiesArrayList.get(i));
                            map.remove(selectedcitiesArrayList.get(i));
                        }
                        wApp.PrefsaveHashMap(map);
                        adapter.notifyDataSetChanged();

                        if (mActionMode != null) {
                            mActionMode.finish();
                        }
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mActionMode = null;
                isMultiSelect = false;
                selectedcitiesArrayList = new ArrayList<String>();
                refreshAdapter();
            }
        };
        citiesRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, citiesRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect)
                    multi_select(position);
            }


            @Override
            public void onItemLongClick(View view, int position) {
                if (!isMultiSelect) {

                    isMultiSelect = true;


                    if (mActionMode == null) {
                        mActionMode = startSupportActionMode(mActionModeCallback);
                    }
                }
                multi_select(position);
            }
        }));

    }

    public void initiateFabButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .build();
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).setFilter(typeFilter)
                                    .build(ListActivity.this);

                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);

                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                HashMap<String, ArrayList<Double>> map = wApp.PrefgetHashMap();
                if (map == null)
                    map = new HashMap<String, ArrayList<Double>>();
                ArrayList<Double> latlng = new ArrayList<Double>();
                latlng.add(place.getLatLng().latitude);
                latlng.add(place.getLatLng().longitude);
                map.put(place.getAddress().toString(), latlng);
                selectedcitiesArrayList = new ArrayList<String>();
                citiesArrayList = new ArrayList<String>();
                citiesList.clear();
                citiesList.addAll(map.keySet());
                citiesArrayList.addAll(citiesList);
                wApp.PrefsaveHashMap(map);
                refreshAdapter();
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    // Add/Remove the item from/to the list

    public void multi_select(int position) {
        if (mActionMode != null) {
            if (selectedcitiesArrayList.contains(citiesArrayList.get(position)))
                selectedcitiesArrayList.remove(citiesArrayList.get(position));
            else
                selectedcitiesArrayList.add(citiesArrayList.get(position));

            if (selectedcitiesArrayList.size() > 0)
                mActionMode.setTitle("" + selectedcitiesArrayList.size());
            else
                mActionMode.setTitle("");

            refreshAdapter();

        }
    }

    public void refreshAdapter() {
        if (adapter != null) {
            adapter.SelectedcitiesArrayList = selectedcitiesArrayList;
            adapter.citiesArrayList = citiesArrayList;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        refreshAdapter();
        super.onResume();
    }

    private void initiateAdapter() {
        citiesArrayList = new ArrayList<String>();
        citiesArrayList.addAll(citiesList);
        adapter = new CitiesListAdapter(ListActivity.this, citiesArrayList, citiesArrayList);
        citiesRecyclerView.setAdapter(adapter);
    }

    private void initiateSet() {
        HashMap<String, ArrayList<Double>> map = wApp.PrefgetHashMap();

        if (map != null && map.size() > 0) {
            citiesList.addAll(wApp.PrefgetHashMap().keySet());
        }


    }


    @Override
    public void onBackPressed() {
        citiesList = null;
        citiesArrayList = null;
        selectedcitiesArrayList = null;
        Intent intent = new Intent();
        setResult(wApp.RESULT_OK, intent);
        this.finish();

    }

}
