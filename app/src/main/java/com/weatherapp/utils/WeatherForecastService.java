package com.weatherapp.utils;

import com.weatherapp.model.Daily;
import com.weatherapp.model.ForecastDataModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherForecastService {
    @GET("forecast/f4115699e8b50c04cae10fa1b00afa07/42.3601,-71.0589")
    Observable<ForecastDataModel> getWeatherForecast();
}
