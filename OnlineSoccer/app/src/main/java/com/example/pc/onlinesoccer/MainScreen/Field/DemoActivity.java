package com.example.pc.onlinesoccer.MainScreen.Field;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.onlinesoccer.R;

public class DemoActivity extends AppCompatActivity {

    String longtitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        longtitude = getIntent().getStringExtra("longtitude");
    }

    public String getLongtitude(){
        return longtitude;
    }
}
