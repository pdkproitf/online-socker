package com.example.pc.onlinesoccer.MainScreen.Match;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by PDKPRO on 28/04/2016.
 */
public class DialogMatchAdd extends Dialog {
    public DialogMatchAdd(Context context,int contentView) {
        super(context);
        setContentView(contentView);
        setTitle("new Match");
    }

    @Override
    public void show() {
        super.show();
    }
}
