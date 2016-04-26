package com.example.pc.onlinesoccer.LoginScreen;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.onlinesoccer.MainScreen.MainActivity;
import com.example.pc.onlinesoccer.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {

    private TextView txvGoToRegister;
    private Button btnLogin;
    private EditText edtMail,edtPass;
    private Firebase root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        root = new Firebase("https://soccernetword.firebaseio.com/");

        mapComponent();
    }

    private void mapComponent(){
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/SVN-SAF.ttf");
        Button btn = (Button) findViewById(R.id.btnLogin);
        btn.setTypeface(tf);

        txvGoToRegister = (TextView) findViewById(R.id.txvGoToRegister);
        txvGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvGoToRegister.setTextColor(Color.parseColor("#ffffff"));
                SpannableString content = new SpannableString("No account yet? Create one");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                txvGoToRegister.setText(content);
                Intent gotoRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(gotoRegister);
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoginFireBase(edtMail.getText().toString(), edtPass.getText().toString());
            }
        });
        edtMail = (EditText) findViewById(R.id.edtEmail_login);
        edtPass = (EditText) findViewById(R.id.edtPass_login);
    }
    private void handleLoginFireBase(String mail,String pass){
        root.authWithPassword(mail, pass, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(getBaseContext(),
                        "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(),
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("uid",authData.getUid().toString());
                startActivity(intent);
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(getBaseContext(),
                        firebaseError.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
