package mikes.dept.tuturu.api;

import mikes.dept.tuturu.model.response.ResponseStations;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by mikes on 18.12.16.
 */

public interface TuturuService {

    @GET("/tutu-ru/hire_android_test/master/allStations.json")
    Observable<ResponseStations> getStations();

    @GET("/tutu-ru/hire_android_test/master/allStations.json")
    Observable<ResponseBody> testRequest();

}
