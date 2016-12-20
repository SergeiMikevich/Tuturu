package mikes.dept.tuturu.screen.list_stations;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.repository.RepositoryProvider;
import mikes.dept.tuturu.utils.Const;
import mikes.dept.tuturu.utils.rxloader.LifecycleHandler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mikes on 17.12.16.
 */

public class ListStationsPresenter implements ListStationsContract.Presenter {

    private final ListStationsContract.View mView;
    private final LifecycleHandler mLifecycleHandler;

    private Const mSOURCE;
    private Long mCityId;
    private String mQuery;

    public ListStationsPresenter(@NonNull LifecycleHandler lifecycleHandler, @NonNull ListStationsContract.View view){
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.init();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onClickButtonSearchingToolbar() {
        mView.showToolbar();
    }

    @Override
    public void onClickButtonToolbar() {
        mView.showSearchingToolbar();
    }

    @Override
    public void setValues(@NonNull Const SOURCE, @NonNull Long cityId) {
        mSOURCE = SOURCE;
        mCityId = cityId;
        switch(mSOURCE){
            case LIST_FROMWHENCE_COUNTRY:
                mView.setHintToolbar(R.string.from_whence_station);
                mView.setTextToolbar(R.string.from_whence_station);
                break;
            case LIST_WHERETO_COUNTRY:
                mView.setHintToolbar(R.string.where_to_station);
                mView.setTextToolbar(R.string.where_to_station);
                break;
            default:
                mView.setEmptyHintToolbar();
                mView.setEmptyTextToolbar();
                break;
        }
    }

    @Override
    public void loadStationsByCityId(@NonNull Long cityId, @NonNull String query) {
        Log.d("TAG", "cityId: " + cityId);
        Long a = 1000L;
        Long b = 1000L;
        if(a.equals(b)){
            Log.d("TAG", "a.equals(b) == true");
        }
        else{
            Log.d("TAG", "a.equals(b) == false");
        }
        if(a == b){
            Log.d("TAG", "a == b == true");
        }
        else{
            Log.d("TAG", "a == b == false");
        }
        RepositoryProvider.provideTuturuRepository()
                .getCities()
                .flatMap(Observable::from)
                .filter(this::filterCitiesBySource)
                .filter(city -> city.getCityId().equals(cityId))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mLifecycleHandler.load(R.id.cities))
                .flatMap(Observable::from)
                .map(City::getStations)
                .flatMap(Observable::from)
                .filter(station -> station.hasQueryDataByStation(query))
                .toList()
                .doOnSubscribe(mView::showLoadingIndicator)
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(mView::showData, throwable -> mView.showError());
    }

    @Override
    public void onQueryChanged(@NonNull String text) {
        mQuery = text.toLowerCase().trim();
        loadStationsByCityId(mCityId, mQuery);
    }

    @Override
    public void onItemClick(@NonNull Station station) {
        mView.navigateStationInfo(mSOURCE, station);
    }

    private Boolean filterCitiesBySource(@NonNull City city){
        switch(mSOURCE){
            case LIST_FROMWHENCE_COUNTRY:
                return city.getIsCitiesFrom();
            case LIST_WHERETO_COUNTRY:
                return !city.getIsCitiesFrom();
            default: return true;
        }
    }

}
