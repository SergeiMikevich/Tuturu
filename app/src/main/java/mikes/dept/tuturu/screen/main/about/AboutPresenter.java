package mikes.dept.tuturu.screen.main.about;

import android.support.annotation.NonNull;

/**
 * Created by mikes on 17.12.16.
 */

public class AboutPresenter implements AboutContract.Presenter {

    private final AboutContract.View mView;

    public AboutPresenter(@NonNull AboutContract.View view){
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

}
