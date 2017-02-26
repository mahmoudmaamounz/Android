
package com.weatherapp.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastDataModel implements Serializable, Parcelable
{

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("minutely")
    @Expose
    private Minutely minutely;
    @SerializedName("hourly")
    @Expose
    private Hourly hourly;
    @SerializedName("daily")
    @Expose
    private Daily daily;
    @SerializedName("flags")
    @Expose
    private Flags flags;
    public final static Creator<ForecastDataModel> CREATOR = new Creator<ForecastDataModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ForecastDataModel createFromParcel(Parcel in) {
            ForecastDataModel instance = new ForecastDataModel();
            instance.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.timezone = ((String) in.readValue((String.class.getClassLoader())));
            instance.offset = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.currently = ((Currently) in.readValue((Currently.class.getClassLoader())));
            instance.minutely = ((Minutely) in.readValue((Minutely.class.getClassLoader())));
            instance.hourly = ((Hourly) in.readValue((Hourly.class.getClassLoader())));
            instance.daily = ((Daily) in.readValue((Daily.class.getClassLoader())));
            instance.flags = ((Flags) in.readValue((Flags.class.getClassLoader())));
            return instance;
        }

        public ForecastDataModel[] newArray(int size) {
            return (new ForecastDataModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2369155193467641426L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ForecastDataModel() {
    }

    /**
     * 
     * @param timezone
     * @param flags
     * @param currently
     * @param longitude
     * @param latitude
     * @param hourly
     * @param offset
     * @param daily
     * @param minutely
     */
    public ForecastDataModel(Double latitude, Double longitude, String timezone, Integer offset, Currently currently, Minutely minutely, Hourly hourly, Daily daily, Flags flags) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.offset = offset;
        this.currently = currently;
        this.minutely = minutely;
        this.hourly = hourly;
        this.daily = daily;
        this.flags = flags;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Minutely getMinutely() {
        return minutely;
    }

    public void setMinutely(Minutely minutely) {
        this.minutely = minutely;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(timezone);
        dest.writeValue(offset);
        dest.writeValue(currently);
        dest.writeValue(minutely);
        dest.writeValue(hourly);
        dest.writeValue(daily);
        dest.writeValue(flags);
    }

    public int describeContents() {
        return  0;
    }

}
