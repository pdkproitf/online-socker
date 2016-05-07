package com.example.pc.onlinesoccer.MainScreen.Match;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pc.onlinesoccer.MainScreen.Field.Fields;
import com.example.pc.onlinesoccer.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Calendar;

public class MatchAddActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spField;
    private EditText edtStartDateDg,edtStartTimeDg,edtEndDateDg,edtEndTimeDg;
    private Button btnStartDateDg,btnStartTimeDg,btnEndDateDg,btnEndTimeDg,btnCreate;
    private SeekBar sbMaxPlayer;
    private ArrayAdapter<String> adapter;
    private ArrayList<Fields> listField;
    private ArrayList<String> listFieldStr;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private TextView txtSeekResult;
    private String field_name,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        this.setContentView(R.layout.activity_match_add);
        getArguments();

        defineComponent();
        setComponentValue();
        addComponentAction();
    }

    @Override
    public void onClick(View v) {
        if (v == btnStartDateDg) {
            showDatePicker(edtStartDateDg);
        }
        if (v == btnStartTimeDg) {
            showTimeCalender(edtStartTimeDg);
        }
        if(v == btnEndDateDg){
            showDatePicker(edtEndDateDg);
        }
        if(v == btnEndTimeDg){
            showTimeCalender(edtEndTimeDg);
        }
        if(v == btnCreate){
            //Toast.makeText(getBaseContext(),"vo day ",Toast.LENGTH_LONG).show();
            getDataSetFirebase();
        }
    }

    private void showDatePicker(final EditText editText){
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
                        editText.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    private void showTimeCalender(final EditText edtText){
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
                        edtText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        tpd.show();
    }

    private void getArguments(){
        Bundle extras = getIntent().getExtras();
        this.listField = (ArrayList<Fields>) extras.get("listField");
        this.user_id = (String) extras.get("uid");
    }

    private void defineComponent(){
        spField = (Spinner) this.findViewById(R.id.spField);

        edtStartDateDg = (EditText) this.findViewById(R.id.edtStartDateDialog);
        edtStartTimeDg = (EditText) this.findViewById(R.id.edtStartTimeDialog);
        edtEndDateDg = (EditText) this.findViewById(R.id.edtEndDateDialog);
        edtEndTimeDg = (EditText) this.findViewById(R.id.edtEndTimeDialog);

        txtSeekResult = (TextView) this.findViewById(R.id.edtSeekResult);

        btnStartDateDg = (Button) this.findViewById(R.id.btnStartDateDialog);
        btnStartTimeDg = (Button) this.findViewById(R.id.btnStartTimeDialog);
        btnEndDateDg = (Button) this.findViewById(R.id.btnEndDateDialog);
        btnEndTimeDg = (Button) this.findViewById(R.id.btnEndTimeDialog);
        btnCreate = (Button) this.findViewById(R.id.btnCreateMatch);

        sbMaxPlayer = (SeekBar) this.findViewById(R.id.sbMaxPlayer);

        txtSeekResult.setText(sbMaxPlayer.getProgress()+"");
    }

    public void addComponentAction(){
        btnStartDateDg.setOnClickListener(this);
        btnStartTimeDg.setOnClickListener(this);
        btnEndDateDg.setOnClickListener(this);
        btnEndTimeDg.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        sbMaxPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSeekResult.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                field_name = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setComponentValue(){
        listFieldStr = new ArrayList<String>();
        for (Fields fields:this.listField) {
            this.listFieldStr.add(fields.getName());
        }
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,this.listFieldStr);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spField.setAdapter(this.adapter);
    }

    private void getDataSetFirebase(){
        Matchs matchs = new Matchs("10",getFieldId(field_name),user_id,sbMaxPlayer.getProgress(),
                1,edtStartTimeDg.getText().toString()+" "+edtStartDateDg.getText().toString(),
                edtEndTimeDg.getText().toString()+" "+edtEndDateDg.getText().toString(),false);

        Firebase matchBranch = new Firebase("https://soccernetword.firebaseio.com/Matchs/");
        matchBranch.push().setValue(matchs, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "SUCCESS", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private String getFieldId(String name){
        for (int i =0; i<listField.size(); i++)
            if(listField.get(i).getName().equals(name))
                return listField.get(i).getId()+"";
        return "11";
    }

}
