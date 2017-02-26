
package com.weatherapp.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Hourly implements Serializable, Parcelable
{

    public final static Creator<Hourly> CREATOR = new Creator<Hourly>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Hourly createFromParcel(Parcel in) {
            Hourly instance = new Hourly();
            return instance;
        }

        public Hourly[] newArray(int size) {
            return (new Hourly[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4695205112942493242L;

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
