package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Tab01DetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMoney;
    TextView tvMsg;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab01_details);

        tvTitle= findViewById(R.id.tv_Title);
        tvMoney= findViewById(R.id.tv_money);
        tvMsg= findViewById(R.id.tv_msg);
        iv= findViewById(R.id.tab01_imgs);



        Intent intent= getIntent();
        String img= intent.getStringExtra("img");

        //이미지 설정 .
        Glide.with(this).load(img).into(iv);

        String title= intent.getStringExtra("title");
        String msg=intent.getStringExtra("msg");
        String money= intent.getStringExtra("money");

//        Toast.makeText(this, ""+img, Toast.LENGTH_SHORT).show();


        Thread t= new Thread(){
            @Override
            public void run() {

                String imgUrl= img;

                try {
                    URL url= new URL(imgUrl);

                    InputStream is= url.openStream();

                    final Bitmap bm= BitmapFactory.decodeStream(is);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvMoney.setText(money);

//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home){
//            super.onBackPressed();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}