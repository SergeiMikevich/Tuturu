package mikes.dept.tuturu.screen.main.searching_routes;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseFragment;
import mikes.dept.tuturu.screen.list_country.ListCountryActivity;
import mikes.dept.tuturu.screen.main.searching_routes.date_picker.DatePickerFragment;
import mikes.dept.tuturu.screen.main.searching_routes.date_picker.OnDateSetListener;
import mikes.dept.tuturu.utils.Const;

public class SearchingRoutesFragment
        extends BaseFragment<SearchingRoutesContract.Presenter>
        implements SearchingRoutesContract.View, OnDateSetListener, View.OnClickListener {

    private static final String SOURCE_KEY = "source_key";
    private static final String STATION_KEY = "station_key";

    private TextView mTvFromWhence;
    private TextView mTvWhereTo;
    private TextView mTvDate;

    public static OnDateSetListener sOnDateSetListener;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == Activity.RESULT_OK && requestCode == 1){
            Station station = intent.getParcelableExtra(STATION_KEY);
            mPresenter.updateStation(station);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_searchingroutes;
    }

    @Override
    protected SearchingRoutesContract.Presenter getPresenter() {
        return new SearchingRoutesPresenter(this);
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void init() {
        sOnDateSetListener = this;
        mTvFromWhence = (TextView) mView.findViewById(R.id.tvFromWhence);
        mTvWhereTo = (TextView) mView.findViewById(R.id.tvWhereTo);
        mTvDate = (TextView) mView.findViewById(R.id.tvDate);
        LinearLayout llFromWhence = (LinearLayout) mView.findViewById(R.id.llFromWhence);
        LinearLayout llWhereTo = (LinearLayout) mView.findViewById(R.id.llWhereTo);
        LinearLayout llDate = (LinearLayout) mView.findViewById(R.id.llDate);
        FloatingActionButton fabReplace = (FloatingActionButton) mView.findViewById(R.id.fabReplace);
        FrameLayout flSearchRoutes = (FrameLayout) mView.findViewById(R.id.flSearchRoutes);
        llFromWhence.setOnClickListener(this);
        llWhereTo.setOnClickListener(this);
        llDate.setOnClickListener(this);
        fabReplace.setOnClickListener(this);
        flSearchRoutes.setOnClickListener(this);
    }

    @Override
    public void showDatePicker() {
        new DatePickerFragment().show(getFragmentManager(), "datePicker");
    }

    @Override
    public void setTextDate(@NonNull String date) {
        mTvDate.setText(date);
    }

    @Override
    public void setTextFromWhence(@NonNull String textFromWhence) {
        mTvFromWhence.setText(textFromWhence);
    }

    @Override
    public void setTextWhereTo(@NonNull String textWhereTo) {
        mTvWhereTo.setText(textWhereTo);
    }

    @Override
    public void navigateList(@NonNull Const SOURCE) {
        Intent intent = new Intent(getActivity(), ListCountryActivity.class);
        intent.putExtra(SOURCE_KEY, SOURCE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onDateSet(int year, int month, int day) {
        mPresenter.onDateSet(year, month, day);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.llFromWhence:
                mPresenter.onClickFromWhence();
                break;
            case R.id.llWhereTo:
                mPresenter.onClickWhereTo();
                break;
            case R.id.llDate:
                mPresenter.onClickDate();
                break;
            case R.id.fabReplace:
                mPresenter.onClickReplace();
                break;
            case R.id.flSearchRoutes:
                mPresenter.onClickSearchRoutes();
                break;
        }
    }
}
