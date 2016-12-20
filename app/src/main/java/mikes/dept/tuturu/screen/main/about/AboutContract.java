package mikes.dept.tuturu.screen.main.about;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.model.content.Station;
import mikes.dept.tuturu.screen.base.BaseContract;
import mikes.dept.tuturu.utils.Const;

/**
 * Created by mikes on 17.12.16.
 */

public interface AboutContract {

    interface View extends BaseContract.View {

        void init();

    }

    interface Presenter extends BaseContract.Presenter {

    }

}
