package mikes.dept.tuturu.screen.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import mikes.dept.tuturu.screen.base.BaseContract;

/**
 * Created by mikes on 21.12.16.
 */

public interface MainContract {

    interface View {

        void init();

        void closeDrawers();

        void navigateSearchingRoutes();

        void navigateAbout();

    }

    interface Presenter extends BaseContract.Presenter {

        void onNavigationItemSelected(@NonNull MenuItem menuItem);

    }

}
