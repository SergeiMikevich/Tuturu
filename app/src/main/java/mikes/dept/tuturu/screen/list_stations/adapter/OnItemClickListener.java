package mikes.dept.tuturu.screen.list_stations.adapter;

import android.support.annotation.NonNull;

import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.content.Station;

/**
 * Created by mikes on 18.12.16.
 */

public interface OnItemClickListener {

    void onItemClick(@NonNull Station station);

}
