
package com.weatherapp.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable, Parcelable
{

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sunriseTime")
    @Expose
    private Integer sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    private Integer sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    private Double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    private Double precipIntensityMax;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("temperatureMin")
    @Expose
    private Double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    private Integer temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    private Double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    private Integer temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    private Double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    private Integer apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    private Double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    private Integer apparentTemperatureMaxTime;
    @SerializedName("dewPoint")
    @Expose
    private Double dewPoint;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("windSpeed")
    @Expose
    private Double windSpeed;
    @SerializedName("windBearing")
    @Expose
    private Integer windBearing;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("cloudCover")
    @Expose
    private Double cloudCover;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("ozone")
    @Expose
    private Double ozone;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    private Integer precipIntensityMaxTime;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("precipAccumulation")
    @Expose
    private Double precipAccumulation;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            Datum instance = new Datum();
            instance.time = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            instance.sunriseTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.sunsetTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.moonPhase = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.precipIntensity = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.precipIntensityMax = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.precipProbability = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.temperatureMin = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.temperatureMinTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.temperatureMax = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.temperatureMaxTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.apparentTemperatureMin = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.apparentTemperatureMinTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.apparentTemperatureMax = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.apparentTemperatureMaxTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.dewPoint = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.humidity = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.windSpeed = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.windBearing = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.visibility = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.cloudCover = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.pressure = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.ozone = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.precipIntensityMaxTime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.precipType = ((String) in.readValue((String.class.getClassLoader())));
            instance.precipAccumulation = ((Double) in.readValue((Double.class.getClassLoader())));
            return instance;
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4451287862984183503L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param temperatureMinTime
     * @param sunsetTime
     * @param summary
     * @param precipIntensityMaxTime
     * @param visibility
     * @param precipIntensity
     * @param precipIntensityMax
     * @param ozone
     * @param time
     * @param apparentTemperatureMaxTime
     * @param precipAccumulation
     * @param icon
     * @param temperatureMaxTime
     * @param pressure
     * @param cloudCover
     * @param apparentTemperatureMinTime
     * @param temperatureMin
     * @param precipType
     * @param dewPoint
     * @param sunriseTime
     * @param windSpeed
     * @param humidity
     * @param moonPhase
     * @param apparentTemperatureMax
     * @param windBearing
     * @param precipProbability
     * @param apparentTemperatureMin
     * @param temperatureMax
     */
    public Datum(Integer time, String summary, String icon, Integer sunriseTime, Integer sunsetTime, Double moonPhase, Double precipIntensity, Double precipIntensityMax, Double precipProbability, Double temperatureMin, Integer temperatureMinTime, Double temperatureMax, Integer temperatureMaxTime, Double apparentTemperatureMin, Integer apparentTemperatureMinTime, Double apparentTemperatureMax, Integer apparentTemperatureMaxTime, Double dewPoint, Double humidity, Double windSpeed, Integer windBearing, Integer visibility, Double cloudCover, Double pressure, Double ozone, Integer precipIntensityMaxTime, String precipType, Double precipAccumulation) {
        super();
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.moonPhase = moonPhase;
        this.precipIntensity = precipIntensity;
        this.precipIntensityMax = precipIntensityMax;
        this.precipProbability = precipProbability;
        this.temperatureMin = temperatureMin;
        this.temperatureMinTime = temperatureMinTime;
        this.temperatureMax = temperatureMax;
        this.temperatureMaxTime = temperatureMaxTime;
        this.apparentTemperatureMin = apparentTemperatureMin;
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        this.apparentTemperatureMax = apparentTemperatureMax;
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.visibility = visibility;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.ozone = ozone;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.precipType = precipType;
        this.precipAccumulation = precipAccumulation;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Integer sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public Integer getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Integer sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public void setTemperatureMinTime(Integer temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Integer getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public void setTemperatureMaxTime(Integer temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    public Integer getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public void setApparentTemperatureMinTime(Integer apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    public Integer getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public void setApparentTemperatureMaxTime(Integer apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Integer windBearing) {
        this.windBearing = windBearing;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Integer getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(Integer precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Double getPrecipAccumulation() {
        return precipAccumulation;
    }

    public void setPrecipAccumulation(Double precipAccumulation) {
        this.precipAccumulation = precipAccumulation;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeValue(sunriseTime);
        dest.writeValue(sunsetTime);
        dest.writeValue(moonPhase);
        dest.writeValue(precipIntensity);
        dest.writeValue(precipIntensityMax);
        dest.writeValue(precipProbability);
        dest.writeValue(temperatureMin);
        dest.writeValue(temperatureMinTime);
        dest.writeValue(temperatureMax);
        dest.writeValue(temperatureMaxTime);
        dest.writeValue(apparentTemperatureMin);
        dest.writeValue(apparentTemperatureMinTime);
        dest.writeValue(apparentTemperatureMax);
        dest.writeValue(apparentTemperatureMaxTime);
        dest.writeValue(dewPoint);
        dest.writeValue(humidity);
        dest.writeValue(windSpeed);
        dest.writeValue(windBearing);
        dest.writeValue(visibility);
        dest.writeValue(cloudCover);
        dest.writeValue(pressure);
        dest.writeValue(ozone);
        dest.writeValue(precipIntensityMaxTime);
        dest.writeValue(precipType);
        dest.writeValue(precipAccumulation);
    }

    public int describeContents() {
        return  0;
    }

}
