package mikes.dept.tuturu.screen.list_city.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;

/**
 * Created by mikes on 18.12.16.
 */

public class ListCityAdapter
        extends RecyclerView.Adapter<ListCityHolder>
        implements View.OnClickListener {

    private final OnItemClickListener mOnItemClickListener;
    private final List<City> mList;

    public ListCityAdapter(@NonNull OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
        mList = new ArrayList<>();
    }

    public void changeDataSet(@NonNull List<City> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ListCityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_list, parent, false);
        return new ListCityHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListCityHolder holder, int position) {
        City city = mList.get(position);
        holder.bind(city);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(city);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        City city = (City) v.getTag();
        mOnItemClickListener.onItemClick(city);
    }
}
