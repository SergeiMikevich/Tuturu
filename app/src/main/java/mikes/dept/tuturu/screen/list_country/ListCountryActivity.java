package mikes.dept.tuturu.screen.list_country;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseActivity;
import mikes.dept.tuturu.screen.list_city.ListCityActivity;
import mikes.dept.tuturu.screen.list_country.adapter.ListCountryAdapter;
import mikes.dept.tuturu.screen.list_country.adapter.OnItemClickListener;
import mikes.dept.tuturu.utils.Const;
import mikes.dept.tuturu.utils.RxEditText;
import mikes.dept.tuturu.utils.rxloader.LifecycleHandler;
import mikes.dept.tuturu.utils.rxloader.LoaderLifecycleHandler;
import mikes.dept.tuturu.widget.DividerItemDecorator;
import mikes.dept.tuturu.widget.loading.LoadingDialog;
import mikes.dept.tuturu.widget.loading.LoadingView;

/**
 * Created by mikes on 17.12.16.
 */

public class ListCountryActivity
        extends BaseActivity<ListCountryContract.Presenter>
        implements ListCountryContract.View, OnItemClickListener {

    private static final String SOURCE_KEY = "source_key";
    private static final String STATION_KEY = "station_key";

    @BindView(R.id.containerSearchingToolbar)
    LinearLayout mContainerSearchingToolbar;

    @BindView(R.id.containerToolbar)
    LinearLayout mContainerToolbar;

    @BindView(R.id.editTextSearchingToolbar)
    RxEditText mEditTextSearchingToolbar;

    @BindView(R.id.textViewToolbar)
    TextView mTextViewToolbar;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ListCountryAdapter mAdapter;

    private LoadingView mLoadingView;

    @Override
    public void navigateListCity(Const SOURCE, @NonNull String countryTitle) {
        //TODO: call static method from list city
        ListCityActivity.navigateListCity(this, SOURCE, countryTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK && requestCode == 1){
            Station station = intent.getParcelableExtra(STATION_KEY);
            Const SOURCE = (Const) getIntent().getSerializableExtra(SOURCE_KEY);
            Log.d("TAG", "SOURCE3: " + SOURCE);
            Intent intentSendBack = new Intent();
            intentSendBack.putExtra(STATION_KEY, station);
            intentSendBack.putExtra(SOURCE_KEY, SOURCE);
            setResult(RESULT_OK, intentSendBack);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected ListCountryContract.Presenter getPresenter() {
        LifecycleHandler loaderLifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        return new ListCountryPresenter(this, loaderLifecycleHandler);
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecorator(this));
        mAdapter = new ListCountryAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mPresenter.setSource((Const) getIntent().getSerializableExtra(SOURCE_KEY));
        mPresenter.loadData("");
        mEditTextSearchingToolbar.setOnRxTextChangeListener(text -> mPresenter.onQueryChanged(text), 1000);
    }

    @Override
    public void showSearchingToolbar() {
        mContainerSearchingToolbar.setVisibility(View.VISIBLE);
        mContainerToolbar.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        mEditTextSearchingToolbar.requestFocus();
    }

    @Override
    public void showToolbar() {
        mContainerSearchingToolbar.setVisibility(View.GONE);
        mContainerToolbar.setVisibility(View.VISIBLE);
        mEditTextSearchingToolbar.setText("");
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextSearchingToolbar.getWindowToken(), 0);
    }

    @Override
    public void setHintToolbar(@NonNull Integer stringId) {
        mEditTextSearchingToolbar.setHint(getString(stringId));
    }

    @Override
    public void setTextToolbar(@NonNull Integer stringId) {
        mTextViewToolbar.setText(getString(stringId));
    }

    @Override
    public void setEmptyHintToolbar() {
        mEditTextSearchingToolbar.setText("");
    }

    @Override
    public void setEmptyTextToolbar() {
        mTextViewToolbar.setText("");
    }

    @Override
    public void showData(@NonNull List<City> cityList) {
        mAdapter.changeDataSet(cityList);
    }

    @Override
    public void showError() {
        mAdapter.changeDataSet(new ArrayList<>());
        Snackbar.make(mRecyclerView, "No data", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingView.showLoadingIndicator();
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingView.hideLoadingIndicator();
    }

    @OnClick(R.id.buttonSearchingToolbar)
    public void onClickButtonSearchingToolbar(){
        mPresenter.onClickButtonSearchingToolbar();
    }

    @OnClick(R.id.buttonToolbar)
    public void onClickButtonToolbar(){
        mPresenter.onClickButtonToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(@NonNull City city) {
        mPresenter.onItemClick(city);
    }

}
