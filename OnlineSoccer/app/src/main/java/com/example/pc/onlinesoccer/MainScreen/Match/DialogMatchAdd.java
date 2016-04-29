package com.example.pc.onlinesoccer.MainScreen.Match;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pc.onlinesoccer.MainScreen.Field.Fields;
import com.example.pc.onlinesoccer.R;

import java.util.ArrayList;

/**
 * Created by PDKPRO on 28/04/2016.
 */
public class DialogMatchAdd extends Dialog{
    //dg = dialog
    private Spinner spField;
    private EditText edtStartDateDg,edtStartTimeDg,edtEndDateDg,edtEndTimeDg;
    private Button btnStartDateDg,btnStartTimeDg,btnEndDateDg,btnEndTimeDg;
    private SeekBar sbMaxPlayer;
    private ArrayAdapter<String> adapter;
    private ArrayList<Fields> lisedteld;
    private ArrayList<String> lisedteldStr;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView txtTime,edtSeekResult;
    public DialogMatchAdd(Context context,int contentView,ArrayList<Fields> lisedteld) {
        super(context);
        this.lisedteld = lisedteld;
        setContentView(contentView);
        setTitle("new Match");
        defineComponent();
        setComponentValue();
        handleAction();

    }

    private void defineComponent(){
        spField = (Spinner) this.findViewById(R.id.spField);

        edtStartDateDg = (EditText) this.findViewById(R.id.edtStartDateDialog);
        edtStartTimeDg = (EditText) this.findViewById(R.id.edtStartTimeDialog);
        edtEndDateDg = (EditText) this.findViewById(R.id.edtEndDateDialog);
        edtEndTimeDg = (EditText) this.findViewById(R.id.edtStartTimeDialog);
        edtSeekResult = (TextView) this.findViewById(R.id.edtSeekResult);

        btnStartDateDg = (Button) this.findViewById(R.id.btnStartDateDialog);
        btnStartTimeDg = (Button) this.findViewById(R.id.btnStartTimeDialog);
        btnEndDateDg = (Button) this.findViewById(R.id.btnEndDateDialog);
        btnEndTimeDg = (Button) this.findViewById(R.id.btnEndTimeDialog);

        sbMaxPlayer = (SeekBar) this.findViewById(R.id.sbMaxPlayer);

    }

    private void setComponentValue(){
        lisedteldStr = new ArrayList<String>();
        for (Fields fields:this.lisedteld) {
            this.lisedteldStr.add(fields.getName());
        }
        this.adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,this.lisedteldStr);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spField.setAdapter(this.adapter);
    }

    private void handleAction(){
        handlerDateTimeAction();
    }

    private void handlerDateTimeAction(){
        this.btnStartDateDg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process to get Current Time
            }
        });
    }

    private void createTimePicker(int mHour,int mMinute,EditText txtTime){

    }


    @Override
    public void show() {
        super.show();
    }

/*
    @Override
    public void onClick(View v) {
        if (v == btnStartDateDg) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            edtStartDateDg.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if (v == btnStartTimeDg) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
    }
    */
}