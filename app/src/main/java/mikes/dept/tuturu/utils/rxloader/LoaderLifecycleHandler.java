package mikes.dept.tuturu.utils.rxloader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import rx.Observable;

/**
 * Created by mikes on 17.12.16.
 */

public class LoaderLifecycleHandler implements LifecycleHandler {

    private final Context mContext;
    private final LoaderManager mLoaderManager;

    @NonNull
    public static LifecycleHandler create(@NonNull Context context, @NonNull LoaderManager loaderManager){
        return new LoaderLifecycleHandler(context, loaderManager);
    }

    public LoaderLifecycleHandler(@NonNull Context context, @NonNull LoaderManager loaderManager){
        mContext = context;
        mLoaderManager = loaderManager;
    }

    @NonNull
    @Override
    public <T> Observable.Transformer<T, T> load(int id) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                if (mLoaderManager.getLoader(id) == null) {
                    mLoaderManager.initLoader(id, Bundle.EMPTY, new RxLoaderCallbacks<>(observable));
                }
                RxLoader<T> loader = (RxLoader<T>) mLoaderManager.getLoader(id);
                return loader.createObservable();
            }
        };
    }

    @NonNull
    @Override
    public <T> Observable.Transformer<T, T> reload(int id) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                if (mLoaderManager.getLoader(id) != null) {
                    mLoaderManager.destroyLoader(id);
                }
                mLoaderManager.initLoader(id, Bundle.EMPTY, new RxLoaderCallbacks<>(observable));
                RxLoader<T> loader = (RxLoader<T>) mLoaderManager.getLoader(id);
                return loader.createObservable();
            }
        };
    }

    @Override
    public void clear(int id) {
        mLoaderManager.destroyLoader(id);
    }

    private class RxLoaderCallbacks<D> implements LoaderManager.LoaderCallbacks<D> {

        private final Observable<D> mObservable;

        public RxLoaderCallbacks(@NonNull Observable<D> observable) {
            mObservable = observable;
        }

        @NonNull
        @Override
        public Loader<D> onCreateLoader(int id, Bundle args) {
            return new RxLoader<>(mContext, mObservable);
        }

        @Override
        public void onLoadFinished(Loader<D> loader, D data) {
            // Do nothing
        }

        @Override
        public void onLoaderReset(Loader<D> loader) {
            // Do nothing
        }
    }

}
