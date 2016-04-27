package com.example.pc.onlinesoccer.MainScreen.Match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pc.onlinesoccer.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PC on 20-Apr-16.
 */


public class FirstFragment extends Fragment {

    private Firebase root;
    private MatchAdapter matchAdapter;
    private ArrayList<Matchs> listMatch;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.match_layout, container, false);
        root = new Firebase("https://soccernetword.firebaseio.com/Matchs");

        listView = (ListView) view.findViewById(R.id.lvMatch);

        matchAdapter = new MatchAdapter(view.getContext(),R.layout.match_item,listMatch = new ArrayList<Matchs>());

        listView.setAdapter(matchAdapter);
        matchAdapter.notifyDataSetChanged();

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateList(dataSnapshot.getKey(),(HashMap)dataSnapshot.getValue(),true);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateItem(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateList(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue(), false);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //updateList(dataSnapshot.getKey(),(HashMap)dataSnapshot.getValue(),false);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;
    }

    private void updateList(String match_id,HashMap hashValue,boolean isAdd){
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
        this.matchAdapter.notifyDataSetChanged();
    }

    private void updateItem(String match_id,HashMap hashValue){
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
        this.matchAdapter.notifyDataSetChanged();
    }

}
