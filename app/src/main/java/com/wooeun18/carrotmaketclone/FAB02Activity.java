package com.wooeun18.carrotmaketclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FAB02Activity extends AppCompatActivity {
    MultipartBody.Part filePart=null;

    EditText etMsg;
    ImageView iv;

    String imgPath; //업로드할 이미지의 절대경로

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_b02);

        etMsg= findViewById(R.id.et_msg);
        iv= findViewById(R.id.iv);
    }

    public void clickBtn3(View view) {
        //이미지 추가
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10 && resultCode==RESULT_OK){
            Uri uri= data.getData();
            if (uri != null){
                Glide.with(this).load(uri).into(iv);

                //이미지 uri 를 절대 주소로 변경해야 파일 업로드가 가능함 .
                //Uri -> 절대경로
                imgPath= getRealPathFromUri(uri);
                //new AlertDialog.Builder(this).setMessage(imgPath).create().show();

            }//if2
        }//if1
    }

    //절대 주소
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    public void clicktv(View view) {
        //확인 버튼 .
        String msg = etMsg.getText().toString();

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);

        if (imgPath!=null){
            File file= new File(imgPath);
            RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"), file);
            filePart= MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        }

        Map<String, String> dataPart= new HashMap<>();
        dataPart.put("msg", msg);
//        dataPart.put("name", name);
//        dataPart.put("title", title);

        Call<String> call= retrofitService.postDataToServer(dataPart,filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(FAB02Activity.this, ""+s, Toast.LENGTH_SHORT).show();
                Log.i("tag",s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(FAB02Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("tag","에러 :"+t.getMessage());

            }
        });

        finish();
    }

}//Appcom













