package mikes.dept.tuturu.api;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.BuildConfig;
import mikes.dept.tuturu.api.interceptor.LoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mikes on 18.12.16.
 */

public class ApiFactory {

    private static OkHttpClient sClient;

    private static TuturuService sService;

    private ApiFactory() {

    }

    public static void recreate(){
        sClient = null;
        sClient = getClient();
        sService = buildRetrofit().create(TuturuService.class);
    }

    @NonNull
    public static TuturuService getTuturuService() {
        TuturuService service = sService;
        if(service == null){
            synchronized(ApiFactory.class){
                service = sService;
                if(service == null){
                    service = sService = buildRetrofit().create(TuturuService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    private static Retrofit buildRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient(){
        OkHttpClient client = sClient;
        if(client == null){
            synchronized(ApiFactory.class){
                client = sClient;
                if(client == null){
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }

}
