
package com.weatherapp.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Daily implements Serializable, Parcelable
{

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<Daily> CREATOR = new Creator<Daily>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Daily createFromParcel(Parcel in) {
            Daily instance = new Daily();
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.data, (com.weatherapp.model.Datum.class.getClassLoader()));
            return instance;
        }

        public Daily[] newArray(int size) {
            return (new Daily[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7598760975771913644L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Daily() {
    }

    /**
     * 
     * @param summary
     * @param icon
     * @param data
     */
    public Daily(String summary, String icon, List<Datum> data) {
        super();
        this.summary = summary;
        this.icon = icon;
        this.data = data;
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
