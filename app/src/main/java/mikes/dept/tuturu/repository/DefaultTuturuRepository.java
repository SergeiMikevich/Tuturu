package mikes.dept.tuturu.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mikes.dept.tuturu.api.ApiFactory;
import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.utils.PreferenceUtils;
import rx.Observable;

/**
 * Created by mikes on 18.12.16.
 */

public class DefaultTuturuRepository implements TuturuRepository {

    @NonNull
    @Override
    public Observable<List<City>> getCities() {
        return Observable.just(PreferenceUtils.isStationsLoaded())
                .flatMap(isStationsLoaded -> {
                    if(isStationsLoaded){
                        Realm realm = Realm.getDefaultInstance();
                        RealmResults<City> cities = realm.where(City.class).findAll();
                        return Observable.just(realm.copyFromRealm(cities));
                    }
                    else{
                        return ApiFactory.getTuturuService()
                                .getStations()
                                .map(responseStations -> {
                                    List<City> cities = new ArrayList<>();
                                    List<City> citiesFrom = new ArrayList<>();
                                    List<City> citiesTo = new ArrayList<>();
                                    for(City city: responseStations.getCitiesFrom()){
                                        city.setIsCitiesFrom(true);
                                        citiesFrom.add(city);
                                    }
                                    for(City city: responseStations.getCitiesTo()){
                                        city.setIsCitiesFrom(false);
                                        citiesTo.add(city);
                                    }
                                    cities.addAll(citiesFrom);
                                    cities.addAll(citiesTo);
                                    return cities;
                                })
                                .flatMap(cities -> {
                                    PreferenceUtils.setStationsLoaded(true);
                                    Realm.getDefaultInstance().executeTransaction(realm -> {
                                        realm.delete(City.class);
                                        realm.insert(cities);
                                    });
                                    return Observable.just(cities);
                                });
                    }
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<City> cities = realm.where(City.class).findAll();
                    return cities.size() == 0 ? Observable.error(new Exception()) : Observable.just(realm.copyFromRealm(cities));
                });
    }
}
