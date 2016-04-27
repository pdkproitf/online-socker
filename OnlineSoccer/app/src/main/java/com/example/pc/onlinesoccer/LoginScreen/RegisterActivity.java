package com.example.pc.onlinesoccer.LoginScreen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.onlinesoccer.MainScreen.Profile.Users;
import com.example.pc.onlinesoccer.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView txvGoToLogin;
    Button btnRegister;
    private Firebase root;
    private EditText edtMail,edtPass,edtPassRetype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Firebase.setAndroidContext(this);
        root = new Firebase("https://soccernetword.firebaseio.com/");


        mapComponent();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!edtPass.getText().equals(edtPassRetype.getText()))
                    handleRegisterFireBase(edtMail.getText().toString(),edtPass.getText().toString());
                else
                   Toast.makeText(getBaseContext(),"Fail Retype pass",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void mapComponent(){
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SVN-SAF.ttf");
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setTypeface(tf);

        txvGoToLogin = (TextView) findViewById(R.id.txvGoToLogin);
        txvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(gotoLogin);
            }
        });

        edtMail = (EditText) findViewById(R.id.edtEmail_register);
        edtPass = (EditText) findViewById(R.id.edtPass_register);
        edtPassRetype = (EditText) findViewById(R.id.edtRetypePass_register);

    }

    private void handleRegisterFireBase(String mail, String passWord) {
        root.createUser(mail, passWord, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Toast.makeText(getBaseContext(),
                        "Successfully created user account with uid: " + result.get("uid"),
                        Toast.LENGTH_LONG).show();
                createProfile(result.get("uid").toString(), edtMail.getText().toString());
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(getBaseContext(),
                        firebaseError.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    private void createProfile(String uid,String mail){
        Firebase.setAndroidContext(this);
        Firebase root = new Firebase("https://soccernetword.firebaseio.com/Users");
        Users profile = new Users("noName",mail,"noAge","noImage","noPhone");
        root.child(uid).setValue(profile);
    }
}
