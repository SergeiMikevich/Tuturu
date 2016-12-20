package mikes.dept.tuturu.screen.list_city;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.repository.RepositoryProvider;
import mikes.dept.tuturu.utils.Const;
import mikes.dept.tuturu.utils.rxloader.LifecycleHandler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mikes on 19.12.16.
 */

public class ListCityPresenter implements ListCityContract.Presenter {

    private final ListCityContract.View mView;
    private final LifecycleHandler mLifecycleHandler;

    private Const mSOURCE;
    private String mCountryTitle;
    private String mQuery;

    public ListCityPresenter(@NonNull ListCityContract.View view, @NonNull LifecycleHandler loaderLifecycleHandler){
        mView = view;
        mLifecycleHandler = loaderLifecycleHandler;
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
    public void setValues(Const SOURCE, @NonNull String countryTitle) {
        mSOURCE = SOURCE;
        mCountryTitle = countryTitle;
        switch(mSOURCE){
            case LIST_FROMWHENCE_COUNTRY:
                mView.setHintToolbar(R.string.from_whence_city);
                mView.setTextToolbar(R.string.from_whence_city);
                break;
            case LIST_WHERETO_COUNTRY:
                mView.setHintToolbar(R.string.where_to_city);
                mView.setTextToolbar(R.string.where_to_city);
                break;
            default:
                mView.setEmptyHintToolbar();
                mView.setEmptyTextToolbar();
                break;
        }
    }

    @Override
    public void loadData(@NonNull String query) {
        RepositoryProvider.provideTuturuRepository()
                .getCities()
                .flatMap(Observable::from)
                .filter(this::filterCitiesBySource)
                .filter(city -> city.getCountryTitle().equals(mCountryTitle))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mLifecycleHandler.load(R.id.cities))
                .flatMap(Observable::from)
                .filter(city -> city.hasQueryDataByCity(query))
                .toList()
                .doOnSubscribe(mView::showLoadingIndicator)
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(mView::showData, throwable -> mView.showError());
    }

    @Override
    public void onQueryChanged(@NonNull String text) {
        mQuery = text.toLowerCase().trim();
        loadData(mQuery);
    }

    @Override
    public void onItemClick(@NonNull City city) {
        mView.navigateListStations(mSOURCE, city);
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
