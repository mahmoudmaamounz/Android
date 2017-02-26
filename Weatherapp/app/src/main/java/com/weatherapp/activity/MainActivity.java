package com.weatherapp.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.androstock.myweatherapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.weatherapp.lists.CityWeatherAdapter;
import com.weatherapp.model.ForecastDataModel;
import com.weatherapp.model.Weather;
import com.weatherapp.utils.ServerFetch;

import io.reactivex.Observable;

import static com.weatherapp.utils.WeatherApplication.wApp;

public class MainActivity extends AppCompatActivity {


    TextView cityField;
    FloatingActionButton cityListButton;
    Typeface weatherFont;
    TextView weatherIcon;
    CityWeatherAdapter adapter;
    RecyclerView weather_forcast_view;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<Weather> weatherObjArray = new ArrayList<Weather>();
    ArrayList<String> citiesList = new ArrayList<String>();
    Observable<ForecastDataModel> dailyData;
    HashMap<String, ArrayList<Double>> map = wApp.PrefgetHashMap();
    String currentCity = "";
    Double Currentlong, Currentlat = 0.0;
    Spinner spinner;
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateLayouts();
        initiateCities();
        initiateSwipeRefreshLayout();
        setTitle("");
    }

    public void initiateCities() {
        currentCity = wApp.PrefgetCurrentCity();

        if (map != null && map.size() > 0) {
            citiesList.addAll(map.keySet());
        } else {
            citiesList.add("Dublin, Ireland");
            citiesList.add("Barcelona, Spain");
            citiesList.add("London, United Kingdom");
            citiesList.add("New York, NY, United States");
            map = new HashMap<String, ArrayList<Double>>();
            ArrayList<Double> latlng = new ArrayList<Double>();
            latlng.add(53.349805);
            latlng.add(-6.260310);
            map.put("Dublin, Ireland", latlng);
            latlng = new ArrayList<Double>();
            latlng.add(41.385064);
            latlng.add(2.173403);
            map.put("Barcelona, Spain", latlng);
            latlng = new ArrayList<Double>();
            latlng.add(51.507351);
            latlng.add(-0.127758);
            map.put("London, United Kingdom", latlng);
            latlng = new ArrayList<Double>();
            latlng.add(40.712784);
            latlng.add(-74.005941);
            map.put("New York, NY, United States", latlng);
            wApp.PrefsaveHashMap(map);
        }
        ArrayList<String> Latlong = new ArrayList<String>();
        if (!TextUtils.isEmpty(currentCity)) {
            Currentlat = map.get(currentCity).get(0);
            Currentlong = map.get(currentCity).get(1);

        } else {
            Currentlat = 41.385064;
            Currentlong = 2.173403;
        }
        Latlong.add("" + Currentlat);
        Latlong.add("" + Currentlong);
        refreshWeather();
    }

    public void initiateLayouts() {
        weather_forcast_view = (RecyclerView) findViewById(R.id.weather_forcast);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        weather_forcast_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        weather_forcast_view.setLayoutManager(mLayoutManager);
        adapter = new CityWeatherAdapter(MainActivity.this, weatherObjArray);
        weather_forcast_view.setAdapter(adapter);
        initiateFabButton();
    }


    public void pullWeatherData(ArrayList<String> lonLat) {
        ServerFetch.placeIdTask asyncTask = new ServerFetch.placeIdTask(new ServerFetch.AsyncResponse() {

            public void processFinish(ArrayList<Weather> weatherArray) {
                weatherObjArray = weatherArray;
                onWeatherRefreshCompleted();
            }
        });
        String[] s = new String[2];
        s[0] = lonLat.get(0);
        s[1] = lonLat.get(1);
        asyncTask.execute(s);
    }

    public void refreshAdapter() {
        if (adapter != null && dataAdapter != null) {
            adapter.weatherObjArray = weatherObjArray;
            map = wApp.PrefgetHashMap();
            citiesList.clear();
            citiesList.addAll(map.keySet());
            dataAdapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        }
    }

    public void initiateSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshWeather();
            }
        });
    }
    public void initiateFabButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.cities_list_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);

        spinner = (Spinner) MenuItemCompat.getActionView(item);
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerlayout, citiesList);
        dataAdapter.setDropDownViewResource(R.layout.spinnerlayout);
        spinner.setAdapter(dataAdapter);
        if (!TextUtils.isEmpty(currentCity))
            spinner.setSelection(citiesList.indexOf(currentCity));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (citiesList != null && citiesList.size() > 0) {
                    String city = citiesList.get(position);
                    wApp.PrefsaveCurrentCity(city);
                    ArrayList<String> lonLat = new ArrayList<String>();
                    Currentlat = map.get(city).get(0);
                    Currentlong = map.get(city).get(1);
                    lonLat.add("" + Currentlat);
                    lonLat.add("" + Currentlong);
                    refreshWeather();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        return true;
    }

    public void refreshWeather() {
        ArrayList<String> s = new ArrayList<String>();
        s.add("" + Currentlat);
        s.add("" + Currentlong);
        pullWeatherData(s);
    }

    public void onWeatherRefreshCompleted() {
        refreshAdapter();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void citiesListClicked(View v) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent,wApp.REQ_MAIN_ACTIVITY_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == wApp.REQ_MAIN_ACTIVITY_CODE) {
            if(resultCode == wApp.RESULT_OK) {
                refreshWeather();
            }
        }
    }
}