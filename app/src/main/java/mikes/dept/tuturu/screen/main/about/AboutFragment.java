package mikes.dept.tuturu.screen.main.about;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.screen.base.BaseFragment;

public class AboutFragment
        extends BaseFragment<AboutContract.Presenter>
        implements AboutContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected AboutContract.Presenter getPresenter() {
        return new AboutPresenter(this);
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void init() {

    }

}
