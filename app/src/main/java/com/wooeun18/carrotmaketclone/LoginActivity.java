package com.wooeun18.carrotmaketclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


public class LoginActivity extends AppCompatActivity {

    CircleImageView civ;
    EditText et;

    Uri imgUri; // 선택된 이미지의 콘텐츠 주소(경로)

    boolean isFirst= true; //처음앱을 실행하여 프로필 데이터기ㅏ 없는가 ?
    boolean isChanged= false; //기존 프로필 이미지를 변경한 적이 있는가 ?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et= findViewById(R.id.et);
        civ= findViewById(R.id.iv);

        //SharedPreferences 에 미리 저장되어있는 닉네임 , 프로필 이미지가 있다면 읽어와러ㅏ
        loadData();
        if (G.nickName != null){
            et.setText(G.nickName);
            Picasso.get().load(G.profileUrl).into(civ);

            //처음이 아니네
            isFirst= false;
        }
    }


    //SharedPreferences 의 저장값들 읽어오는 기능 메소드 .
    public void loadData(){
        SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
        G.nickName= pref.getString("nickName", null);
        G.profileUrl= pref.getString("profileUrl", null);
    }


    public void clickImage(View view) {
        //사진 or 갤러리 앱에서 사진 선택하도록
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK){
            imgUri= data.getData();
            Picasso.get().load(imgUri).into(civ); //피카소는 외부저장소 동적 퍼미션이 자동 적용됨 .

            //사진이 변경
            isChanged= true;
        }
    }

    public void clickBtn(View view) {

//        Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();
        //처음이거나 사ㅣ진이 변경되었다면
        if (isFirst || isChanged){
            //닉네임과 프로필 이미지를 Firebase storage와 DB 에 저장 후 채팅화면으로 이동 - 서버에 저장해야 다른폰에서 이미지가 보여짐 .
            saveData();
        }else {
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    //닉네임, 프로필 이미지 저장하는 기능 메소드
    void saveData(){
        //et 에 작성한 닉네임 바로 가져오기 .
        G.nickName= et.getText().toString();

        if (imgUri == null){
            Toast.makeText(this, "사진선택 해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        //우선 이미지 업로드가 오래걸리므로 , 먼저 수행
        //Firebase Storage 에 먼저 업로드

        //fire storage 에 중복된 파일명이 있으면 안도ㅔ사 .
        SimpleDateFormat sdg= new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName= sdg.format(new Date()) + ".png";

        //firebase storage에 이미지 업로드 .
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        StorageReference imgRef = firebaseStorage.getReference("profileImage/" + fileName);

        UploadTask uploadTas = imgRef.putFile(imgUri);
        uploadTas.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //업로드 된 파일에 다운로드 주소(서버에 있는 이미지의 인터넷 경로 URL) 를 얻어오도록
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //firebase 저장소에 저장되어 있는 이미지에 대한
                        //다운로드 주소(uri)를 문자열로 얻기
                        G.profileUrl= uri.toString();
                        Toast.makeText(LoginActivity.this, "저장 완료 \n"+ G.profileUrl, Toast.LENGTH_SHORT).show();

                        //1. 서버의 DB에 닉네임과 이미지url 을 저장 .
                        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                        //"profiles' 라는 이름의 자식노드 참조 (없으면 생성 , 있으면 참조)
                        DatabaseReference profilesRef = firebaseDatabase.getReference("profiles");

                        //닉네임을 '키'값으로 하고 '값'을 이미지 경로로 저장 .
                        profilesRef.child(G.nickName).setValue(G.profileUrl);

                        //앱을 처음 실행할대만 닉네임과 사진을 입력하도록 하기위해
                        //2. SharedPreferences 에 저장 (내부저장소에 데이터를 영구적으로 저장하는 녀석)
                        SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
                        SharedPreferences.Editor editor =pref.edit();

                        editor.putString("nickName", G.nickName);
                        editor.putString("profileUrl", G.profileUrl);

                        editor.commit();

                        //저장이 완료 되었으니 채팅화면으로 전환 .
                        Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();

                    }
                });

            }
        });
    }

    public void clickImg(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("입장 방법");
        builder.setMessage("1. 프로필 사진 넣기 \n 2. 닉네임 입력하기 \n 3. 확인누르기");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}//Main











