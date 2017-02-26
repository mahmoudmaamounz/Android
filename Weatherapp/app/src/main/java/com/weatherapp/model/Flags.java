
package com.weatherapp.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Flags implements Serializable, Parcelable
{

    public final static Creator<Flags> CREATOR = new Creator<Flags>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Flags createFromParcel(Parcel in) {
            Flags instance = new Flags();
            return instance;
        }

        public Flags[] newArray(int size) {
            return (new Flags[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2786524439361409290L;

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
