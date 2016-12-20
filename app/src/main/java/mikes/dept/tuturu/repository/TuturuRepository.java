package mikes.dept.tuturu.repository;

import android.support.annotation.NonNull;

import java.util.List;

import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.response.ResponseStations;
import rx.Observable;

/**
 * Created by mikes on 18.12.16.
 */

public interface TuturuRepository {

    @NonNull
    Observable<List<City>> getCities();

}
