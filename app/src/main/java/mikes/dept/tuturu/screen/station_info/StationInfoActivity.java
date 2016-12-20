package mikes.dept.tuturu.screen.station_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseActivity;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 19.12.16.
 */

public class StationInfoActivity
        extends BaseActivity<StationInfoContract.Presenter>
        implements StationInfoContract.View, AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private static final String SOURCE_KEY = "source_key";
    private static final String STATION_KEY = "station_key";

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.textViewCountry)
    TextView mTextViewCountry;

    @BindView(R.id.textViewCity)
    TextView mTextViewCity;

    @BindView(R.id.textViewStation)
    TextView mTextViewStation;

    @BindView(R.id.buttonDone)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static void navigateStationInfo(@NonNull AppCompatActivity activity, @NonNull Const SOURCE, @NonNull Station station) {
        Intent intent = new Intent(activity, StationInfoActivity.class);
        intent.putExtra(STATION_KEY, station);
        intent.putExtra(SOURCE_KEY, SOURCE);
        ActivityCompat.startActivityForResult(activity, intent, 1, Bundle.EMPTY);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_station_info;
    }

    @Override
    protected StationInfoContract.Presenter getPresenter() {
        return new StationInfoPresenter(this);
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        Const SOURCE = (Const) getIntent().getSerializableExtra(SOURCE_KEY);
        Station station = getIntent().getParcelableExtra(STATION_KEY);
        mPresenter.setValues(SOURCE, station);
        mPresenter.loadData();
        mAppBarLayout.addOnOffsetChangedListener(this);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setTextTitle(@NonNull Integer stringId) {
        mCollapsingToolbarLayout.setTitle(getString(stringId));
    }

    @Override
    public void setTextCountry(@NonNull String country) {
        mTextViewCountry.setText(country);
    }

    @Override
    public void setTextCity(@NonNull String city) {
        mTextViewCity.setText(city);
    }

    @Override
    public void setTextStation(@NonNull String station) {
        mTextViewStation.setText(station);
    }

    @Override
    public void showFloatingActionButton() {
        ViewCompat.animate(mFloatingActionButton).scaleY(1).scaleX(1).start();
    }

    @Override
    public void hideFloatingActionButton() {
        ViewCompat.animate(mFloatingActionButton).scaleY(0).scaleX(0).start();
    }

    @OnClick(R.id.buttonDone)
    public void onClickButtonDone(){
        Station station = getIntent().getParcelableExtra(STATION_KEY);
        Const SOURCE = (Const) getIntent().getSerializableExtra(SOURCE_KEY);
        Intent intent = new Intent();
        intent.putExtra(STATION_KEY, station);
        intent.putExtra(SOURCE_KEY, SOURCE);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mPresenter.onOffsetChanged(appBarLayout.getTotalScrollRange(), verticalOffset);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
