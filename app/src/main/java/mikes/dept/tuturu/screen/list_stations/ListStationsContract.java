package mikes.dept.tuturu.screen.list_stations;

import android.support.annotation.NonNull;

import java.util.List;

import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseContract;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 17.12.16.
 */

public interface ListStationsContract {

    interface View extends BaseContract.View {

        void init();

        void showSearchingToolbar();

        void showToolbar();

        void setHintToolbar(@NonNull Integer stringId);

        void setTextToolbar(@NonNull Integer stringId);

        void setEmptyHintToolbar();

        void setEmptyTextToolbar();

        void showData(@NonNull List<Station> stationList);

        void showError();

        void navigateStationInfo(Const SOURCE, @NonNull Station station);

    }

    interface Presenter extends BaseContract.Presenter {

        void onClickButtonSearchingToolbar();

        void onClickButtonToolbar();

        void setValues(@NonNull Const SOURCE, @NonNull Long cityId);

        void loadStationsByCityId(@NonNull Long cityId, @NonNull String query);

        void onQueryChanged(@NonNull String text);

        void onItemClick(@NonNull Station station);

    }

}
