package mikes.dept.tuturu.screen.list_stations.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.content.Station;

/**
 * Created by mikes on 18.12.16.
 */

public class ListStationsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewItemList)
    TextView mTextViewItemList;

    public ListStationsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Station station){
        mTextViewItemList.setText(station.getStationTitle());
    }

}
