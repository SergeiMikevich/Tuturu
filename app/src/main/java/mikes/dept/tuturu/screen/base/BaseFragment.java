package mikes.dept.tuturu.screen.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mikes on 17.12.16.
 */

public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment {

    protected P mPresenter;

    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mPresenter = getPresenter();
        mPresenter.onCreate();
        return mView;
    }

    public void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    public void onResume(){
        super.onResume();
        mPresenter.onResume();
    }

    public void onPause(){
        super.onPause();
        mPresenter.onPause();
    }

    public void onStop(){
        super.onStop();
        mPresenter.onStop();
    }

}
