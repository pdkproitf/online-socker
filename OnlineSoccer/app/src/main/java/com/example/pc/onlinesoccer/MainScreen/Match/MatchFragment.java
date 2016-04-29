package com.example.pc.onlinesoccer.MainScreen.Match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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


public class MatchFragment extends Fragment {

    private Firebase root;
    private MatchAdapter adapterMatch;
    private ArrayList<Matchs> listMatch;
    private ListView listView;
    private Button btnCreate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_match_layout, container, false);
        root = new Firebase("https://soccernetword.firebaseio.com/Matchs");

        defineComponent(view);
        handlerFirebaseAction();
        handlerCreateMatch();
        handlerViewAction();

        return view;
    }

    public void defineComponent(View view){
        listView = (ListView) view.findViewById(R.id.lvMatch);

        adapterMatch = new MatchAdapter(view.getContext(),R.layout.content_match_item,listMatch = new ArrayList<Matchs>());

        listView.setAdapter(adapterMatch);
        adapterMatch.notifyDataSetChanged();
        //
        btnCreate = (Button) view.findViewById(R.id.btnCreateMatch);
    }

    public void handlerFirebaseAction(){
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateList(dataSnapshot.getKey(), (HashMap) dataSnapshot.getValue(), true);
                //Toast.makeText(getContext(), dataSnapshot.getKey().toString() + "  s=> " + s, Toast.LENGTH_LONG).show();
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
    }

    public void handlerViewAction(){
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DialogMatchDetails dialog = new DialogMatchDetails(getContext(),R.layout.content_match_show_dialog,listMatch.get(position));
                dialog.show();
            }
        });
    }

    public void handlerCreateMatch(){
        this.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        this.adapterMatch.notifyDataSetChanged();
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
        this.adapterMatch.notifyDataSetChanged();
    }

}
