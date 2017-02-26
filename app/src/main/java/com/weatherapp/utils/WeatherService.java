package com.weatherapp.utils;

import com.weatherapp.model.Daily;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {



    public static WeatherForecastService createForecastClient() {
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.darksky.net/").build();
        WeatherForecastService weatherforecast = retrofit.create(WeatherForecastService.class);
        return weatherforecast;
    }
}