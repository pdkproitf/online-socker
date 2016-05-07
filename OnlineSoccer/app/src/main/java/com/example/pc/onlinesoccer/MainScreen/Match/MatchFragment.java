package com.example.pc.onlinesoccer.MainScreen.Match;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.pc.onlinesoccer.MainScreen.Field.Fields;
import com.example.pc.onlinesoccer.MainScreen.Profile.Users;
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


public class MatchFragment extends Fragment {

    private Firebase root;
    private MatchAdapter adapterMatch;
    private ArrayList<Matchs> listMatch;
    private ArrayList<Fields> listField;
    private ArrayList<Users> listUser;
    private ArrayList<Slots> listSlot;
    private ListView listView;
    private Button btnCreate;
    private String userId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_match_layout, container, false);
        root = new Firebase("https://soccernetword.firebaseio.com/");

        this.userId = getArguments().getString("uid").toString();

        defineComponent(view);
        handlerFirebaseAction();
        handlerCreateMatch();
        handlerViewAction();

        return view;
    }

    private void defineComponent(View view){
        listView = (ListView) view.findViewById(R.id.lvMatch);

        listField = new ArrayList<Fields>();
        listUser = new ArrayList<Users>();
        listSlot = new ArrayList<Slots>();
        adapterMatch = new MatchAdapter(view.getContext(),R.layout.content_match_item,listMatch = new ArrayList<Matchs>(), listField);

        listView.setAdapter(adapterMatch);
        adapterMatch.notifyDataSetChanged();
        //
        btnCreate = (Button) view.findViewById(R.id.btnCreateMatch);
    }

    private void handlerFirebaseAction() {
        handlerFieldFirebase();
        handlerMatchFireBase();
        handlerUserFirebase();
        handlerSlotFirebase();
    }

    private void handlerViewAction(){
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DialogMatchDetails dialog = new DialogMatchDetails(getContext(), listMatch.get(position),
                        getFieldName(Integer.parseInt(listMatch.get(position).getField_id())),
                        getUserName(listMatch.get(position).getHost_id()));
                dialog.show();
            }
        });
    }

    private void handlerCreateMatch(){
        this.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MatchAddActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("listField", listField);
                intent.putExtra("uid", userId);
                startActivity(intent);
            }
        });
    }

    private void handlerMatchFireBase() {
        root.child("Matchs").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateMatchList(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue(), true);
                //Toast.makeText(getContext(), dataSnapshot.getKey().toString() + "  s=> " + s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateMatchItem(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateMatchList(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue(), false);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //updateList(dataSnapshot.getKey(),(HashMap)dataSnapshot.getValue(),false);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void handlerFieldFirebase(){
        root.child("Fields").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateFieldList(Integer.parseInt(dataSnapshot.getKey().toString()), (HashMap) dataSnapshot.getValue(), true);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateFieldItem(Integer.parseInt(dataSnapshot.getKey()), (HashMap) dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateFieldList(Integer.parseInt(dataSnapshot.getKey().toString()), (HashMap) dataSnapshot.getValue(), false);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void handlerUserFirebase(){
        root.child("Profiles").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateUserList(dataSnapshot.getKey().toString(), (HashMap) dataSnapshot.getValue(), true);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateUserItem(dataSnapshot.getKey().toString(), (HashMap) dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateUserList(dataSnapshot.getKey().toString(), (HashMap) dataSnapshot.getValue(), false);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void handlerSlotFirebase(){
        root.child("Slots").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateSlotList(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue(), true);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //updateSlotList(dataSnapshot.getKey(),(HashMap)dataSnapshot.getValue(),true);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateSlotList(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue(), true);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void updateMatchList(String match_id,HashMap hashValue,boolean isAdd){
        if(isAdd){
            Matchs matchs = new Matchs(match_id,hashValue.get("field_id").toString(),
                    hashValue.get("host_id").toString(),Integer.parseInt(hashValue.get("maxPlayer").toString()),
                    Integer.parseInt(hashValue.get("status").toString()),hashValue.get("startTime").toString(),
                    hashValue.get("endTime").toString(),(boolean)hashValue.get("verified"));
            this.listMatch.add(matchs);
        }else{
            for (Matchs matchs:this.listMatch) {
                if(matchs.getId().equals(match_id))
                    this.listMatch.remove(matchs);
            }
        }
        this.adapterMatch.notifyDataSetChanged();
    }

    private void updateMatchItem(String match_id,HashMap hashValue){
        Matchs matchs = new Matchs(match_id,hashValue.get("field_id").toString(),
                hashValue.get("host_id").toString(),Integer.parseInt(hashValue.get("maxPlayer").toString()),
                Integer.parseInt(hashValue.get("status").toString()),hashValue.get("startTime").toString(),
                hashValue.get("endTime").toString(),(boolean)hashValue.get("verified"));
        for (int i = 0; i < this.listMatch.size(); i++) {
            if (this.listMatch.get(i).getId().equals(match_id)){
                this.listMatch.set(i,matchs);
                break;
            }
        }
        this.adapterMatch.notifyDataSetChanged();
    }

    private void updateFieldList(int field_id,HashMap hash,boolean isAdd){
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
    }

    private void updateFieldItem(int field_id,HashMap hash){
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
    }

    private void updateUserList(String userId,HashMap hashValue,boolean isAdd){
        if(isAdd){
            Users user = new Users(userId,hashValue.get("name").toString());
            this.listUser.add(user);
        }else{
            for (Users users:this.listUser) {
                if(users.getId().equals(userId))
                    this.listMatch.remove(userId);
            }
        }
        this.adapterMatch.notifyDataSetChanged();
    }

    private void updateUserItem(String userId,HashMap hashValue){
        Users  users = new Users(userId,hashValue.get("name").toString());
        for (int i = 0; i < this.listUser.size(); i++) {
            if (this.listUser.get(i).getId().equals(userId)){
                this.listUser.set(i,users);
                break;
            }
        }
        this.adapterMatch.notifyDataSetChanged();
    }

    private void updateSlotList(String id,HashMap hashValue,boolean isAdd) {
        Slots slots = new Slots(id,hashValue.get("match_id").toString(),Integer.parseInt(hashValue.get("quantity").toString()));
        if (isAdd) {
            this.listSlot.add(slots);
        } else {
            for (Slots slot : this.listSlot) {
                if (slot.getId().equals(id))
                    this.listSlot.remove(id);
            }
        }
        updateSlotInMatchList(slots,isAdd);
    }

    private void updateSlotInMatchList(Slots slots,boolean isAdd){
        for (Matchs match:listMatch) {
            if(match.getId().equals(slots.getMatch_id())){
                if(isAdd){
                    int status = match.getStatus() + slots.getQuantity();
                    match.setStatus(status);
                }else{
                    int status = match.getStatus() - slots.getQuantity();
                    match.setStatus(status);
                }
                break;
            }
        }
        this.adapterMatch.notifyDataSetChanged();
    }

    private String getFieldName(int id){
        for (int i =0; i<listField.size(); i++)
            if(listField.get(i).getId()==id)
                return listField.get(i).getName();
        return id+"";
    }

    private String getUserName(String id){
        for (int i =0; i<listUser.size(); i++)
            if(listUser.get(i).getId().equals(id))
                return listUser.get(i).getName();
        return id+"";
    }

}