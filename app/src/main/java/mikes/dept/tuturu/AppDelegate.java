package mikes.dept.tuturu;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import mikes.dept.tuturu.api.ApiFactory;
import mikes.dept.tuturu.repository.RepositoryProvider;
import ru.arturvasilov.sqlite.core.SQLite;

/**
 * Created by mikes on 17.12.16.
 */

public class AppDelegate extends Application {

    private static Context sContext;

    @Override
    public void onCreate(){
        super.onCreate();
        sContext = this;

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();

        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);

        SQLite.initialize(this);

        ApiFactory.recreate();
        RepositoryProvider.init();

    }

    @NonNull
    public static Context getContext(){
        return sContext;
    }

}
