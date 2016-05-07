package com.example.pc.onlinesoccer.MainScreen.Field;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pc.onlinesoccer.R;

public class MapActivity extends AppCompatActivity {

    public String longtitude,latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        longtitude = getIntent().getStringExtra("longtitude");

        Bundle bundle = new Bundle();
        bundle.putString("longtitude", longtitude);

        //bundle.putString("latitude",latitude);
        //set Fragmentclass Arguments
        MapFragment fragobj = new MapFragment();
        fragobj.setArguments(bundle);

        //getFragmentManager().beginTransaction().replace(R.id.containerView)
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
