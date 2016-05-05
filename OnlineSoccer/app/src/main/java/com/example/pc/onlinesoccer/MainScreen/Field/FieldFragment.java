package com.example.pc.onlinesoccer.MainScreen.Field;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc.onlinesoccer.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by PC on 20-Apr-16.
 */

public class FieldFragment extends Fragment {
    private Firebase root;
    private FieldAdapter fieldAdapter;
    private ArrayList<Fields> listField;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.second_layout, container, false);
        root = new Firebase("https://soccernetword.firebaseio.com/Fields");

        listView = (ListView) view.findViewById(R.id.lvField);

        fieldAdapter = new FieldAdapter(view.
                getContext(),R.layout.field_item,listField = new ArrayList<Fields>());

        listView.setAdapter(fieldAdapter);
        fieldAdapter.notifyDataSetChanged();

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateList(Integer.parseInt(dataSnapshot.getKey()),(HashMap)dataSnapshot.getValue(),true);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateItem(Integer.parseInt(dataSnapshot.getKey()), (HashMap) dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateList(Integer.parseInt(dataSnapshot.getKey()), (HashMap) dataSnapshot.getValue(), false);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //updateList(dataSnapshot.getKey(),(HashMap)dataSnapshot.getValue(),false);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fields currentField = (Fields) fieldAdapter.getItem(position);
                //Toast.makeText(getContext(), currentField.getLongtitude(), Toast.LENGTH_SHORT).show();
                Intent myIntent=new Intent(getActivity(), Main2Activity.class);
                myIntent.putExtra("longtitude",currentField.getLongtitude());
                myIntent.putExtra("latitude",currentField.getLatitude());

                startActivity(myIntent);
            }
        });
        return view;
    }


    private void updateList(int field_id,HashMap hash,boolean isAdd){
        if(isAdd){
            String temp[] = {"canada","gb","south_korea","sweden","usa","vietnam"};
            Random rand = new Random();
            int j = rand.nextInt(6);
            int count = Integer.parseInt(hash.get("countStadium").toString());
            int special = Integer.parseInt(hash.get("priceSpecial").toString());
            int normal = Integer.parseInt(hash.get("priceNormal").toString());
            double latitude = Double.parseDouble(hash.get("latitude").toString());
            double longtitude = Double.parseDouble(hash.get("longtitude").toString());
            Fields fields = new Fields(field_id,hash.get("name").toString(),
                    hash.get("address").toString(),hash.get("phone").toString(),
                    count,special,normal,temp[j],latitude, longtitude);
            this.listField.add(fields);
        }else{
            for (Fields fields:this.listField) {
                if(fields.getId() == field_id)
                    this.listField.remove(fields);
            }
        }
        this.fieldAdapter.notifyDataSetChanged();
    }

    private void updateItem(int field_id,HashMap hash){
        String temp[] = {"canada","gb","south_korea","sweden","usa","vietnam"};
        Random rand = new Random();
        int j = rand.nextInt(6);
        int count = Integer.parseInt(hash.get("countStadium").toString());
        int special = Integer.parseInt(hash.get("priceSpecial").toString());
        int normal = Integer.parseInt(hash.get("priceNormal").toString());
        double latitude = Double.parseDouble(hash.get("latitude").toString());
        double longtitude = Double.parseDouble(hash.get("longtitude").toString());
        Fields fields = new Fields(field_id,hash.get("name").toString(),
                hash.get("address").toString(),hash.get("phone").toString(),
                count,special,normal,temp[j],latitude, longtitude);
        for (int i = 0; i < this.listField.size(); i++) {
            if (this.listField.get(i).getId() == field_id){
                this.listField.set(i,fields);
                break;
            }
        }
        this.fieldAdapter.notifyDataSetChanged();
    }
}
