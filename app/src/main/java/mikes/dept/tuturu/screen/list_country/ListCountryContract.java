package mikes.dept.tuturu.screen.list_country;

import android.support.annotation.NonNull;

import java.util.List;

import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.screen.base.BaseContract;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 17.12.16.
 */

public interface ListCountryContract {

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

        void navigateListCity(Const SOURCE, @NonNull String countryTitle);

    }

    interface Presenter extends BaseContract.Presenter {

        void onClickButtonSearchingToolbar();

        void onClickButtonToolbar();

        void setSource(Const SOURCE);

        void loadData(@NonNull String query);

        void onQueryChanged(@NonNull String text);

        void onItemClick(@NonNull City city);

    }

}
