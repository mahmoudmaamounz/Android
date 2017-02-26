
package com.weatherapp.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Minutely implements Serializable, Parcelable
{

    public final static Creator<Minutely> CREATOR = new Creator<Minutely>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Minutely createFromParcel(Parcel in) {
            Minutely instance = new Minutely();
            return instance;
        }

        public Minutely[] newArray(int size) {
            return (new Minutely[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2947795465224913996L;

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
