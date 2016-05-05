package com.example.pc.onlinesoccer.MainScreen.Field;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.onlinesoccer.R;

public class Main2Activity extends AppCompatActivity {

    public String longtitude,latitude;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //longtitude = getIntent().getStringExtra("longtitude");

        mFragmentManager = getSupportFragmentManager();
        Bundle bundle = getIntent().getExtras();
        //bundle.putString("longtitude", longtitude);

        //bundle.putString("latitude",latitude);
        //set Fragmentclass Arguments
        MapFragment fragobj = new MapFragment();
        fragobj.setArguments(bundle);

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapcontainer,fragobj).commit();
    }
}
