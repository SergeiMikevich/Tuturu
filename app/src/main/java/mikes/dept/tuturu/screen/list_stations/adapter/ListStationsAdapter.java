package mikes.dept.tuturu.screen.list_stations.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;
import mikes.dept.tuturu.model.content.Station;

/**
 * Created by mikes on 18.12.16.
 */

public class ListStationsAdapter
        extends RecyclerView.Adapter<ListStationsHolder>
        implements View.OnClickListener {

    private final OnItemClickListener mOnItemClickListener;
    private final List<Station> mList;

    public ListStationsAdapter(@NonNull OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
        mList = new ArrayList<>();
    }

    public void changeDataSet(@NonNull List<Station> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ListStationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_list, parent, false);
        return new ListStationsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListStationsHolder holder, int position) {
        Station station = mList.get(position);
        holder.bind(station);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(station);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        Station station = (Station) v.getTag();
        mOnItemClickListener.onItemClick(station);
    }
}
