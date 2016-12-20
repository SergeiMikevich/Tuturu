package mikes.dept.tuturu.screen.station_info;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseContract;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 19.12.16.
 */

public interface StationInfoContract {

    interface View extends BaseContract.View {

        void init();

        void setTextTitle(@NonNull Integer stringId);

        void setTextCountry(@NonNull String country);

        void setTextCity(@NonNull String city);

        void setTextStation(@NonNull String station);

        void showFloatingActionButton();

        void hideFloatingActionButton();

    }

    interface Presenter extends BaseContract.Presenter {

        void setValues(@NonNull Const SOURCE, @NonNull Station station);

        void loadData();

        void onOffsetChanged(int totalScrollRange, int verticalOffset);

    }

}
