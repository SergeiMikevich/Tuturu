package mikes.dept.tuturu.model.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by mikes on 18.12.16.
 */

public class Station extends RealmObject implements Parcelable {

    @SerializedName("countryTitle")
    private String mCountryTitle;

    @SerializedName("point")
    private Point mPoint;

    @SerializedName("districtTitle")
    private String mDistrictTitle;

    @SerializedName("cityId")
    private Integer mCityId;

    @SerializedName("cityTitle")
    private String mCityTitle;

    @SerializedName("regionTitle")
    private String mRegionTitle;

    @SerializedName("stationId")
    private Integer mStationId;

    @SerializedName("stationTitle")
    private String mStationTitle;

    public Station(){

    }

    protected Station(Parcel in) {
        mCountryTitle = in.readString();
        mDistrictTitle = in.readString();
        mCityTitle = in.readString();
        mRegionTitle = in.readString();
        mStationTitle = in.readString();
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    @NonNull
    public String getCountryTitle(){
        return mCountryTitle;
    }

    @NonNull
    public Point getPoint(){
        return mPoint;
    }

    @NonNull
    public String getDistrictTitle(){
        return mDistrictTitle;
    }

    @NonNull
    public Integer getCityId(){
        return mCityId;
    }

    @NonNull
    public String getCityTitle(){
        return mCityTitle;
    }

    @NonNull
    public String getRegionTitle(){
        return mRegionTitle;
    }

    @NonNull
    public Integer getStationId(){
        return mStationId;
    }

    @NonNull
    public String getStationTitle(){
        return mStationTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCountryTitle);
        dest.writeString(mDistrictTitle);
        dest.writeString(mCityTitle);
        dest.writeString(mRegionTitle);
        dest.writeString(mStationTitle);
    }

    public boolean hasQueryDataByStation(String query){
        return getStationTitle().toLowerCase().contains(query);
    }

}
