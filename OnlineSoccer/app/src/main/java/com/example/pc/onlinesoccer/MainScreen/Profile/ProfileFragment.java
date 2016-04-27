package com.example.pc.onlinesoccer.MainScreen.Profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.onlinesoccer.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.util.ArrayList;


/**
 * Created by PC on 20-Apr-16.
 */
public class ProfileFragment extends Fragment{

    private EditText edtName,edtMail,edtDate,edtPhone;
    private ImageView imgProfile,imgUpdate;
    private TextView tvProfile,tvMail;
    private Button btnPassword;
    private Firebase root;
    private ArrayList<String> list;
    private Users profile;
    private static int SELECT_PICTURE   = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_layout, container, false);
        root = new Firebase("https://soccernetword.firebaseio.com/Profiles/"+this.getArguments().get("uid").toString());

        createComponet(view);
        actionButton();
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                handlerDataFireBase(dataSnapshot);
                //Toast.makeText(getContext(),dataSnapshot.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                handlerDataFireBase(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                handlerDataFireBase(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;
    }

    private void handlerDataFireBase(DataSnapshot dataSnapshot){
        switch (dataSnapshot.getKey()) {
            case "mail":
                edtMail.setText(dataSnapshot.getValue().toString());
                tvMail.setText(dataSnapshot.getValue().toString());
                break;
            case "name":
                edtName.setText(dataSnapshot.getValue().toString());
                tvProfile.setText(dataSnapshot.getValue().toString());
                break;
            case "date":
                edtDate.setText(dataSnapshot.getValue().toString());
                break;
            case "phone":
                edtPhone.setText(dataSnapshot.getValue().toString());
                break;
            case "imageStr":
                if (dataSnapshot.getValue().toString().equals("noImage")) {
                    Bitmap image = BitmapFactory.decodeResource(getView().getResources(), R.drawable.ic_action_user_add);
                    Bitmap imageBm = Bitmap.createScaledBitmap(image, 70, 70, false);
                    imgProfile.setImageBitmap(imageBm);
                } else {
                    imgProfile.setImageBitmap(getImageBitmap(dataSnapshot.getValue().toString()));
                }
                break;
        }
    }

    public void actionButton(){
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] manHinh = ImageView_To_Byte(imgProfile);
                String chuoi = Base64.encodeToString(manHinh, Base64.DEFAULT);

                Users p = new Users(edtName.getText().toString(), edtMail.getText().toString(), edtDate.getText().toString(), chuoi,edtPhone.getText().toString());
                root.setValue(p);
            }
        });

        //
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });

    }

    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, SELECT_PICTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                imgProfile.setImageBitmap(getBitmapFromUri(uri));
            }
        }
    }
    private Bitmap getBitmapFromUri(Uri uri) {
        Bitmap image = null;
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContext().getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
        }catch (Exception e){}
        return image;
    }

    private void createComponet(View view){
        list = new ArrayList<>();
        profile = new Users();
        edtName = (EditText) view.findViewById(R.id.edtName);
        edtMail = (EditText) view.findViewById(R.id.edtMail);
        edtDate = (EditText) view.findViewById(R.id.edtDate);
        edtPhone =(EditText) view.findViewById(R.id.edtPhone);
        imgUpdate = (ImageView) view.findViewById(R.id.btnUpdate);
        //btnPassword = (Button) view.findViewById(R.id.btnPassword);
        imgProfile = (ImageView) view.findViewById(R.id.imgProfile);
        tvProfile = (TextView) view.findViewById(R.id.tvUser);
        tvMail = (TextView) view.findViewById(R.id.tvMail);
    }
    public Bitmap getImageBitmap(String imageStr){
        byte[] arrImage = Base64.decode(imageStr, Base64.DEFAULT);
        Bitmap imageBm = BitmapFactory.decodeByteArray(arrImage, 0, arrImage.length);
        return  imageBm;
    }
    public byte[] ImageView_To_Byte(ImageView image){
        //Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.chomuc);
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
