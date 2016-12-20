package mikes.dept.tuturu.screen.base;

import mikes.dept.tuturu.widget.loading.LoadingView;

/**
 * Created by mikes on 17.12.16.
 */

public interface BaseContract {

    interface View extends LoadingView {

    }

    interface Presenter{

        void onCreate();

        void onStart();

        void onResume();

        void onPause();

        void onStop();

    }

}
