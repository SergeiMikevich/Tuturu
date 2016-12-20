package mikes.dept.tuturu.screen.main.searching_routes;

import android.support.annotation.NonNull;
import android.util.Log;

import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.utils.Const;
import mikes.dept.tuturu.utils.Date;

/**
 * Created by mikes on 17.12.16.
 */

public class SearchingRoutesPresenter implements SearchingRoutesContract.Presenter {

    private final SearchingRoutesContract.View mView;

    private int FLAG_CLICKED;
    private final int FLAG_FROM_WHENCE = 123;
    private final int FLAG_WHERE_TO = 321;

    private Station stationFromWhence = null;
    private Station stationWhereTo = null;

    public SearchingRoutesPresenter(@NonNull SearchingRoutesContract.View view){
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
    public void onClickFromWhence() {
        FLAG_CLICKED = FLAG_FROM_WHENCE;
        mView.navigateList(Const.LIST_FROMWHENCE_COUNTRY);
    }

    @Override
    public void onClickWhereTo() {
        FLAG_CLICKED = FLAG_WHERE_TO;
        mView.navigateList(Const.LIST_WHERETO_COUNTRY);
    }

    @Override
    public void onClickDate() {
        mView.showDatePicker();
    }

    @Override
    public void onClickReplace() {
        Station station = stationFromWhence;
        stationFromWhence = stationWhereTo;
        stationWhereTo = station;
        String fromWhenceTitle = stationFromWhence != null ? stationFromWhence.getStationTitle() : "";
        String whereToTitle = stationWhereTo != null ? stationWhereTo.getStationTitle() : "";
        mView.setTextFromWhence(fromWhenceTitle);
        mView.setTextWhereTo(whereToTitle);
    }

    @Override
    public void onClickSearchRoutes() {
        Log.d("TAG", "on click search routes");
    }

    @Override
    public void onDateSet(int year, int month, int dayOfMonth) {
        mView.setTextDate(Date.formatDateToString(year, month, dayOfMonth));
    }

    @Override
    public void updateStation(@NonNull Station station) {
        switch(FLAG_CLICKED){
            case FLAG_FROM_WHENCE:
                stationFromWhence = station;
                mView.setTextFromWhence(station.getStationTitle());
                break;
            case FLAG_WHERE_TO:
                stationWhereTo = station;
                mView.setTextWhereTo(station.getStationTitle());
                break;
        }
    }
}
