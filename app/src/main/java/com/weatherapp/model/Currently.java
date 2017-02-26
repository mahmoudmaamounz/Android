
package com.weatherapp.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Currently implements Serializable, Parcelable
{

    public final static Creator<Currently> CREATOR = new Creator<Currently>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Currently createFromParcel(Parcel in) {
            Currently instance = new Currently();
            return instance;
        }

        public Currently[] newArray(int size) {
            return (new Currently[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3519665759894281951L;

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
