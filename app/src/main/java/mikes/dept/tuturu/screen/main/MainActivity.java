package mikes.dept.tuturu.screen.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikes.dept.tuturu.R;
import mikes.dept.tuturu.screen.base.BaseActivity;
import mikes.dept.tuturu.screen.main.about.AboutFragment;
import mikes.dept.tuturu.screen.main.searching_routes.SearchingRoutesFragment;

/**
 * Created by mikes on 20.12.16.
 */

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.nv_drawer;
    }

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mNavigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void closeDrawers() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void navigateSearchingRoutes() {
        SearchingRoutesFragment fragment = new SearchingRoutesFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
        setTitle(getString(R.string.searching_route));
    }

    @Override
    public void navigateAbout() {
        AboutFragment fragment = new AboutFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
        setTitle(getString(R.string.about));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mPresenter.onNavigationItemSelected(menuItem);
        return true;
    }

}