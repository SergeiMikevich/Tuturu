package mikes.dept.tuturu.model.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by mikes on 18.12.16.
 */

public class City extends RealmObject implements Parcelable {

    private Boolean mIsCitiesFrom;

    @SerializedName("countryTitle")
    private String mCountryTitle;

    @SerializedName("point")
    private Point mPoint;

    @SerializedName("districtTitle")
    private String mDistrictTitle;

    @SerializedName("cityId")
    private Long mCityId;

    @SerializedName("cityTitle")
    private String mCityTitle;

    @SerializedName("regionTitle")
    private String mRegionTitle;

    @SerializedName("stations")
    private RealmList<Station> mStations;

    public City(){

    }

    protected City(Parcel in) {
        mCountryTitle = in.readString();
        mDistrictTitle = in.readString();
        mCityTitle = in.readString();
        mRegionTitle = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @NonNull
    public Boolean getIsCitiesFrom(){
        return mIsCitiesFrom;
    }

    public void setIsCitiesFrom(@NonNull Boolean isCitiesFrom){
        mIsCitiesFrom = isCitiesFrom;
    }

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
    public Long getCityId(){
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
    public List<Station> getStations(){
        return mStations;
    }

    public boolean hasQueryDataByCountry(String query){
        return getCountryTitle().toLowerCase().contains(query);
    }

    public boolean hasQueryDataByCity(String query){
        return getCityTitle().toLowerCase().contains(query);
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
    }
}
