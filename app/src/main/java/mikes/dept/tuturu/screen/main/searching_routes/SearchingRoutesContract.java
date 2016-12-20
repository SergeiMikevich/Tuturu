package mikes.dept.tuturu.screen.main.searching_routes;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseContract;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 17.12.16.
 */

public interface SearchingRoutesContract {

    interface View extends BaseContract.View {

        void init();

        void showDatePicker();

        void setTextDate(@NonNull String date);

        void setTextFromWhence(@NonNull String textFromWhence);

        void setTextWhereTo(@NonNull String textWhereTo);

        void navigateList(@NonNull Const SOURCE);

    }

    interface Presenter extends BaseContract.Presenter {

        void onClickFromWhence();

        void onClickWhereTo();

        void onClickDate();

        void onClickReplace();

        void onClickSearchRoutes();

        void onDateSet(int year, int month, int dayOfMonth);

        void updateStation(@NonNull Station station);

    }

}
