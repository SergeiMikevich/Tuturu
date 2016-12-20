package mikes.dept.tuturu.screen.list_city.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikes.dept.tuturu.R;
import mikes.dept.tuturu.model.content.City;

/**
 * Created by mikes on 18.12.16.
 */

public class ListCityHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewItemList)
    TextView mTextViewItemList;

    public ListCityHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull City city){
        mTextViewItemList.setText(city.getCityTitle());
    }

}
