package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_details);

        tvMsg= findViewById(R.id.det_msg);
        tvName= findViewById(R.id.det_name);
        civ= findViewById(R.id.det_civ);
        iv= findViewById(R.id.det_img);

        Intent intent= getIntent();
        String img= intent.getStringExtra("img");

        Glide.with(this).load(img).into(iv);

        String name= intent.getStringExtra("name");
        String msg=intent.getStringExtra("msg");

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


    //업 버튼 클릭 반응하기 - 사실 업버든은 일종의 옵션메뉴아이템과 같은 것 .
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== android.R.id.home){
            //Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
            //디바이스의 뒤로가기 벝튼(back버튼) 을 누른것 처럶 .,
            //finish(); //그냥 엑티비티를 종료시키는 메소드 (back버튼과 살짝 다름)
            super.onBackPressed(); //엑티비티의 백 버튼 클릭시 발동하는 콜백 메소드를 강제로 호출 .
        }
        return super.onOptionsItemSelected(item);
    }

}//1