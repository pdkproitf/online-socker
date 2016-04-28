package com.example.pc.onlinesoccer.MainScreen.Match;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.onlinesoccer.R;

/**
 * Created by PDKPRO on 28/04/2016.
 */
public class DialogMatchDetails extends Dialog {
    private Matchs matchs;
    public DialogMatchDetails(Context context,int contentView,Matchs matchs) {
        super(context);
        this.setContentView(contentView);
        this.matchs = matchs;
        setTitle(matchs.getId()+"");
        //setDattaToDialog(matchs);
    }

    public void handlerButtonAction(){
        Button btnJoin = (Button)this.findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"da join",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void show() {
        TextView tvHost = (TextView) this.findViewById(R.id.tvHostDialog);
        TextView tvField = (TextView) this.findViewById(R.id.tvFieldDialog);
        TextView tvStart = (TextView) this.findViewById(R.id.tvStartTimeDialog);
        TextView tvEndTime = (TextView) this.findViewById(R.id.tvEndTimeDialog);
        TextView tvMaxP  = (TextView) this.findViewById(R.id.tvMaxPlayerDialog);
        TextView tvStatus = (TextView) this.findViewById(R.id.tvStatuDialog);
        CheckBox cb = (CheckBox) this.findViewById(R.id.cbVerifiedDialog);

        tvHost.setText("pdk");
        tvField.setText(matchs.getField_id());
        tvStart.setText(matchs.getStartTime());
        tvEndTime.setText(matchs.getEndTime());
        tvMaxP.setText(matchs.getMaxPlayer()+"");
        tvStatus.setText(matchs.getStatus()+"");
        cb.setChecked(matchs.isVerified());

        handlerButtonAction();
        super.show();
    }
}
