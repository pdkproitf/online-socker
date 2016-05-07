package com.example.pc.onlinesoccer.MainScreen.Match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.onlinesoccer.MainScreen.Field.Fields;
import com.example.pc.onlinesoccer.R;

import java.util.ArrayList;

/**
 * Created by PDKPRO on 27/04/2016.
 */
public class MatchAdapter extends ArrayAdapter {
    private int resource;
    private LayoutInflater inflater;
    private ArrayList<Matchs> arrMatch;
    private ArrayList<Fields> listField;
    public MatchAdapter(Context context, int resource,ArrayList<Matchs> arrMatch,ArrayList<Fields> listField) {
        super(context, resource,arrMatch);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrMatch = arrMatch;
        this.resource = resource;
        this.listField = listField;
    }

    @Override
    public void remove(Object object) {
        super.remove(object);
    }

    @Override
    public void insert(Object object, int index) {
        super.insert(object, index);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.content_match_item,parent,false);
        }

        TextView tvMatchId = (TextView) convertView.findViewById(R.id.tvMatch_id);
        TextView tvField = (TextView) convertView.findViewById(R.id.tvField);
        TextView tvStartTime = (TextView) convertView.findViewById(R.id.tvStartTime);
        TextView tvEndTime = (TextView) convertView.findViewById(R.id.tvEndTime);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tvMatch_status);


        Matchs match = this.arrMatch.get(position);

        tvMatchId.setText(match.getId());
        tvField.setText(getFieldName(Integer.parseInt(match.getField_id())));
        tvStartTime.setText(match.getStartTime().toString());
        tvEndTime.setText(match.getEndTime().toString());
        tvStatus.setText(((match.getMaxPlayer() - match.getStatus())>0? "blank "+(match.getMaxPlayer() - match.getStatus()):"Full"));

        return convertView;
    }

    private String getFieldName(int id){
        for (int i = 0; i < this.listField.size(); i++) {
            if(this.listField.get(i).getId()==id)
                return this.listField.get(i).getName();
        }
        return "unInvaliable";
    }

}