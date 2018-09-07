package com.clakestudio.pc.everyday.settings;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;


/**
 * Created by Jan on 9/7/2018.
 */

public class TimePickerFragment extends DialogFragment {


   @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

       Calendar calendar = Calendar.getInstance();

        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
    }

}
