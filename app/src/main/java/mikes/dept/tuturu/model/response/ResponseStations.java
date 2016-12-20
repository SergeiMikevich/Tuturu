package mikes.dept.tuturu.model.response;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mikes.dept.tuturu.model.content.City;

/**
 * Created by mikes on 18.12.16.
 */

public class ResponseStations {

    @SerializedName("citiesFrom")
    private List<City> mCitiesFrom;

    @SerializedName("citiesTo")
    private List<City> mCitiesTo;

    @NonNull
    public List<City> getCitiesFrom(){
        return mCitiesFrom;
    }

    @NonNull
    public List<City> getCitiesTo(){
        return mCitiesTo;
    }

}
