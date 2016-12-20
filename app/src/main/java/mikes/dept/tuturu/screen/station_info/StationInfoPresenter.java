package mikes.dept.tuturu.screen.station_info;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 19.12.16.
 */

public class StationInfoPresenter implements StationInfoContract.Presenter {

    private StationInfoContract.View mView;

    private Const mSOURCE;
    private Station mStation;

    private boolean mIsImageHidden = false;

    public StationInfoPresenter(@NonNull StationInfoContract.View view){
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
    public void setValues(@NonNull Const SOURCE, @NonNull Station station) {
        mSOURCE = SOURCE;
        mStation = station;
    }

    @Override
    public void loadData() {
        switch(mSOURCE){
            case LIST_FROMWHENCE_COUNTRY:
                mView.setTextTitle(R.string.from_whence);
                break;
            case LIST_WHERETO_COUNTRY:
                mView.setTextTitle(R.string.where_to);
                break;
        }
        mView.setTextCountry(mStation.getCountryTitle());
        mView.setTextCity(mStation.getCityTitle());
        mView.setTextStation(mStation.getStationTitle());
    }

    @Override
    public void onOffsetChanged(int totalScrollRange, int verticalOffset) {
        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100 / totalScrollRange;
        if (currentScrollPercentage >= 20) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                mView.hideFloatingActionButton();
            }
        }
        if (currentScrollPercentage < 20) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                mView.showFloatingActionButton();
            }
        }
    }
}
