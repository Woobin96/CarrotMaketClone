package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class Tab02DetailsActivity extends AppCompatActivity {

    TextView tvName, tvMsg;
    CircleImageView civ;
    ImageView iv;
    String img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_details);

        tvMsg= findViewById(R.id.det_msg);
        tvName= findViewById(R.id.det_name);
        civ= findViewById(R.id.det_civ);
        iv= findViewById(R.id.det_img);

        Intent intent= getIntent();
        img= intent.getStringExtra("img");

//        Picasso.get().load(img).into(iv);

        String name= intent.getStringExtra("name");
        String msg=intent.getStringExtra("msg");

        img= img.replace("./","/");
//        img= img.replace(".png",".PNG");


        Thread t= new Thread(){
            @Override
            public void run() {

                String imgURL= "http://binwoo.dothome.co.kr/Bluemaket"+img;
                try {
                    URL url= new URL(imgURL);
                    InputStream is= url.openStream();

                    final Bitmap bm= BitmapFactory.decodeStream(is);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(Tab02DetailsActivity.this, ""+img, Toast.LENGTH_LONG).show();
                            iv.setImageBitmap(bm);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        loadDB();
        if (G.profileUrl != null){
            tvName.setText(G.nickName);
            Picasso.get().load(G.profileUrl).into(civ);
        }
        tvMsg.setText(msg);
    }

    void loadDB(){
        SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
        G.nickName= pref.getString("nickName", null);
        G.profileUrl= pref.getString("profileUrl", null);
    }

}//1