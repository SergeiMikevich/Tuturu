package mikes.dept.tuturu.screen.list_city;

import android.support.annotation.NonNull;

import java.util.List;

import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.screen.base.BaseContract;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 19.12.16.
 */

public interface ListCityContract {

    interface View extends BaseContract.View {

        void init();

        void showSearchingToolbar();

        void showToolbar();

        void setHintToolbar(@NonNull Integer stringId);

        void setTextToolbar(@NonNull Integer stringId);

        void setEmptyHintToolbar();

        void setEmptyTextToolbar();

        void showData(@NonNull List<City> cityList);

        void showError();

        void navigateListStations(@NonNull Const SOURCE, @NonNull City city);

    }

    interface Presenter extends BaseContract.Presenter {

        void onClickButtonSearchingToolbar();

        void onClickButtonToolbar();

        void setValues(Const SOURCE, @NonNull String countryTitle);

        void loadData(@NonNull String query);

        void onQueryChanged(@NonNull String text);

        void onItemClick(@NonNull City city);

    }

}
