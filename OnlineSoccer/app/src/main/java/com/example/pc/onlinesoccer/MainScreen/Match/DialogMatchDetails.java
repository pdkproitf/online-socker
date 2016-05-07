package com.example.pc.onlinesoccer.MainScreen.Match;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.onlinesoccer.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by PDKPRO on 28/04/2016.
 */
public class DialogMatchDetails extends Dialog {
    private Matchs matchs;
    private String fieldNamse;
    private TextView tvHost,tvField,tvStart,tvEndTime,tvMaxP,tvStatus;
    private CheckBox cb;
    private String userId;
    public DialogMatchDetails(Context context,Matchs matchs,String fieldNamse,String userId) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        this.setContentView(R.layout.content_match_show_dialog);
        this.matchs = matchs;
        this.fieldNamse = fieldNamse;
        this.userId = userId;
        defineComponent();
    }

    public void handlerButtonAction(){
        final Button btnJoin = (Button)this.findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnJoin.setTextColor(Color.WHITE);
                Firebase rootSlot = new Firebase("https://soccernetword.firebaseio.com/Slots");
                Slots slots = new Slots(matchs.getId(),userId);
                rootSlot.push().setValue(slots, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if(firebaseError!=null){
                            Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(),"SUCCESS",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public void defineComponent(){
        tvHost = (TextView) this.findViewById(R.id.tvHostDialog);
        tvField = (TextView) this.findViewById(R.id.tvFieldDialog);
        tvStart = (TextView) this.findViewById(R.id.tvStartTimeDialog);
        tvEndTime = (TextView) this.findViewById(R.id.tvEndTimeDialog);
        tvMaxP  = (TextView) this.findViewById(R.id.tvMaxPlayerDialog);
        tvStatus = (TextView) this.findViewById(R.id.tvStatuDialog);
        cb = (CheckBox) this.findViewById(R.id.cbVerifiedDialog);
    }

    @Override
    public void show() {
        tvHost.setText(userId);
        tvField.setText(fieldNamse);
        tvStart.setText(matchs.getStartTime());
        tvEndTime.setText(matchs.getEndTime());
        tvMaxP.setText(matchs.getMaxPlayer()+"");
        tvStatus.setText(matchs.getStatus()+"");
        cb.setChecked(matchs.isVerified());

        handlerButtonAction();
        super.show();
    }
}