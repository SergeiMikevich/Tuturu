package mikes.dept.tuturu.model.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by mikes on 18.12.16.
 */

public class Point extends RealmObject implements Parcelable {

    @SerializedName("longitude")
    Double mLongitude;

    @SerializedName("latitude")
    Double mLatitude;

    public Point(){

    }

    protected Point(Parcel in) {
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

    @NonNull
    public Double getLongitude(){
        return mLongitude;
    }

    @NonNull
    public Double getLatitude(){
        return mLatitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
