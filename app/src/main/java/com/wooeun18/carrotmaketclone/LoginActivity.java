package com.wooeun18.carrotmaketclone;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    EditText et;
    CircleImageView civ;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et= findViewById(R.id.et);
        civ= findViewById(R.id.iv);



    }

    public void clickImage(View view) {

    }

    public void clickBtn(View view) {
    }
}