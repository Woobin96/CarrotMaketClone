package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FABActivity extends AppCompatActivity {

    ImageView iv;
    Uri imgUri;

    EditText etTitle, etMoney, etMsg;

    String imgPath; //TODO ex83

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_b);
        iv= findViewById(R.id.iv);
        etTitle= findViewById(R.id.et_title);
        etMoney= findViewById(R.id.et_money);
        etMsg= findViewById(R.id.et_text);


    }

    public void clickBtn2(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK){
            imgUri= data.getData();
            Picasso.get().load(imgUri).into(iv); //피카소는 외부저장소 동적 퍼미션이 자동 적용됨 .
        }
    }

    //확인
    public void clicktv(View view) {

        String title= etTitle.getText().toString();
        String msg= etMsg.getText().toString();
        String money= etMoney.getText().toString();

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getReference();

        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName= sdf.format(new Date()) + ".png";//원본파일명을 알려면 절대주소까지 구해야해서 시간상 무조건 png 로함 jpg도 문제없음
        StorageReference imgRef= firebaseStorage.getReference("uploads/"+ fileName); //폴더가 없으면 알아서 만들어줌
        UploadTask uploadTask = imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(FABActivity.this, "성공", Toast.LENGTH_SHORT).show();

                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                         String iv= uri.toString();
                         FAB01 fab01 = new FAB01(title, msg, money, iv);
                        DatabaseReference fab01a= databaseReference.child("FAB01");
                        fab01a.push().setValue(fab01);

                        fab01a.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        finish();
                    }
                });

            }
        });

    }
}














