package mikes.dept.tuturu.screen.main.searching_routes.date_picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import static mikes.dept.tuturu.screen.main.searching_routes.SearchingRoutesFragment.sOnDateSetListener;

/**
 * Created by mikes on 17.12.16.
 */

public class DatePickerFragment
        extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        sOnDateSetListener.onDateSet(year, month, dayOfMonth);
    }

}
