package com.weatherapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.androstock.myweatherapp.R;
import com.weatherapp.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.weatherapp.utils.ServerFetch.Strings.CLEAR_DAY;
import static com.weatherapp.utils.WeatherApplication.wApp;


public class ServerFetch {
    public enum Strings {
        CLEAR_DAY("clear-day"),
        CLEAR_NIGHT("clear-night"),RAIN("rain"),
        SNOW("snow"),
        SLEET("sleet"), WIND("wind"),
        FOG("fog"),
        CLOUDY("cloudy"),
        PARTLY_CLOUDY_DAY("partly-cloudy-day"),
        PARTLY_CLOUDY_NIGHT("partly-cloudy-night");

        private static final Map<String, Strings> map = new HashMap<>();


        static {
            for (Strings en : values()) {
                map.put(en.text, en);
            }
        }

        public static Strings valueFor(String name) {
            return map.get(name);
        }

        private final String text;

        /**
         * @param text
         */
        private Strings(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }

    public enum StringIcons {
        CLEAR_DAY("&#xf00d"),
        CLEAR_NIGHT("&#xf02e"),
        RAIN("&#xf019"),
        SNOW("&#xf01b"),
        SLEET("&#xf0b5"),
        WIND("&#xf021"),
        FOG("&#xf014"),
        CLOUDY("&#xf013"),
        PARTLY_CLOUDY_DAY("&#xf002"),
        PARTLY_CLOUDY_NIGHT("&#xf086");


        private final String text;

        /**
         * @param text
         */
        private StringIcons(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }

    private static final String OPEN_WEATHER_MAP_URL =
            "https://api.darksky.net/forecast/%s/%s,%s";
    private static final String OPEN_WEATHER_MAP_API = "f4115699e8b50c04cae10fa1b00afa07";

    public static Drawable setWeatherIcon(String s) {
        Strings ss = Strings.valueFor(s);
        Drawable iconValue;
        switch (ss) {
            case CLEAR_DAY:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.clearday);
            case CLEAR_NIGHT:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.clearnight);
                break;
            case CLOUDY:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.cloudyweather);
                break;
            case FOG:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.fog);
                break;
            case RAIN:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.rain);
                break;
            case PARTLY_CLOUDY_DAY:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.partlycloudy);
                break;
            case PARTLY_CLOUDY_NIGHT:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.partlycloudynight);
                break;
            case SLEET:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.sleet);
                break;
            case SNOW:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.snow);
                break;
            case WIND:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.wind);
                break;
            default:
                iconValue = wApp.c.getResources().getDrawable(R.drawable.clearday);
        }
        return iconValue;
    }


    public interface AsyncResponse {

        void processFinish(ArrayList<Weather> weatherObjArray);
    }


    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {

        public AsyncResponse delegate = null;//Call back interface

        public placeIdTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            JSONObject jsonWeather = null;
            try {
                jsonWeather = getWeatherJSON(params[0], params[1]);
            } catch (Exception e) {
                Log.e("Error", "Cannot process JSON results", e);
            }


            return jsonWeather;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if (json != null) {
                    JSONArray data = json.getJSONObject("daily").getJSONArray("data");
                    ArrayList<Weather> weatherObjArray = new ArrayList<Weather>();
                    for (int i = 0; i < data.length(); i++) {

                        JSONObject dataObj = data.getJSONObject(i);
                        DateFormat df = DateFormat.getDateTimeInstance();
                        String description = dataObj.getString("summary").toUpperCase(Locale.US);
                        String max_temp = String.format("%1d", (int) (dataObj.getDouble("temperatureMax"))) + " °F";
                        String min_temp = String.format("%1d", (int) (dataObj.getDouble("temperatureMin"))) + " °F";
                        String humidity = dataObj.getString("humidity") + "%";
                        String pressure = dataObj.getString("pressure") + " hPa";
                        String updatedOn = df.format(new Date(dataObj.getLong("time") * 1000));
                        Drawable icon = setWeatherIcon(dataObj.getString("icon"));
                        Weather w = new Weather(description, min_temp, max_temp, pressure, humidity, updatedOn, icon);
                        weatherObjArray.add(w);
                    }


                    delegate.processFinish(weatherObjArray);

                }
            } catch (JSONException e) {
                Log.e("", "Cannot process JSON results", e);
            }


        }
    }


    public static JSONObject getWeatherJSON(String lon, String lat) {
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_URL, OPEN_WEATHER_MAP_API, lon, lat));
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

//			connection.addRequestProperty("x-api-key", OPEN_WEATHER_MAP_API);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());
            return data;
        } catch (Exception e) {
            Log.e("JSON ERROR", e.toString());
            return null;
        }
    }


}