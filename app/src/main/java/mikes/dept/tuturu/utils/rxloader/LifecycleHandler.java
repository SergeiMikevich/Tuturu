package mikes.dept.tuturu.utils.rxloader;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Created by mikes on 17.12.16.
 */

public interface LifecycleHandler {

    @NonNull
    <T> Observable.Transformer<T, T> load(int id);

    @NonNull
    <T> Observable.Transformer<T, T> reload(int id);

    void clear(int id);

}
