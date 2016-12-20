package mikes.dept.tuturu.screen.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import mikes.dept.tuturu.R;

/**
 * Created by mikes on 21.12.16.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;

    public MainPresenter(@NonNull MainContract.View view){
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.init();
        mView.navigateSearchingRoutes();
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
    public void onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mView.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.booking:
                mView.navigateSearchingRoutes();
                break;
            case R.id.about:
                mView.navigateAbout();
                break;
        }
    }
}
