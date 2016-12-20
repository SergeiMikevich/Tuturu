package mikes.dept.tuturu.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

/**
 * Created by mikes on 17.12.16.
 */

public class PreferenceUtils {

    private static final String IS_STATIONS_LOADED = "is_stations_loaded";

    public static void setStationsLoaded(@NonNull Boolean isStationsLoaded){
        Hawk.put(IS_STATIONS_LOADED, isStationsLoaded);
    }

    @NonNull
    public static Boolean isStationsLoaded(){
        return Hawk.get(IS_STATIONS_LOADED, false);
    }

}
