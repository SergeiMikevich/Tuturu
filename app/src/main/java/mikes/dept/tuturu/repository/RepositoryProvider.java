package mikes.dept.tuturu.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by mikes on 18.12.16.
 */

public class RepositoryProvider {

    private static TuturuRepository sRepository;

    private RepositoryProvider(){

    }

    @NonNull
    public static TuturuRepository provideTuturuRepository(){
        if(sRepository == null){
            sRepository = new DefaultTuturuRepository();
        }
        return sRepository;
    }

    @MainThread
    public static void init(){
        sRepository = new DefaultTuturuRepository();
    }

}
