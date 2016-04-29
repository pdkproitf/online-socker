package com.example.pc.onlinesoccer.MainScreen.Field;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.pc.onlinesoccer.R;

public class Main2Activity extends AppCompatActivity {

    TextView edt;
    String longtitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        longtitude = getIntent().getStringExtra("longtitude");
        edt = (TextView) findViewById(R.id.editText);
        edt.setText(longtitude);
    }

    public String getLongtitude(){
        return longtitude;
    }
}
