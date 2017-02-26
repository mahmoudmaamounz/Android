package com.weatherapp.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherApplication extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    public static WeatherApplication wApp;
    public final static String PREF_KEY_CITIES = "com.androstock.weatherapp.ListActivity.CITIES";
    public final static String PREF_KEY_CURRENT_CITY = "com.androstock.weatherapp.ListActivity.DEFAULTCITY";
    public final static int REQ_MAIN_ACTIVITY_CODE = 100;
    public final static int RESULT_OK = 1;


    static Context c;

    @Override
    public void onCreate() {
        super.onCreate();
        wApp = this;
        c = this.getApplicationContext();
    }


    public void PrefsaveHashMap(HashMap<String, ArrayList<Double>> obj) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(PREF_KEY_CITIES, json);
        editor.apply();
    }


    public HashMap<String, ArrayList<Double>> PrefgetHashMap() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        Gson gson = new Gson();
        String json = prefs.getString(PREF_KEY_CITIES, "");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<Double>>>() {
        }.getType();
        HashMap<String, ArrayList<Double>> obj = gson.fromJson(json, type);
        return obj;
    }

    public void PrefsaveCurrentCity(String city) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_KEY_CURRENT_CITY, "");
        editor.apply();     // This line is IMPORTANT !!!
    }

    public String PrefgetCurrentCity() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getString(PREF_KEY_CURRENT_CITY, "");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}