package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {

    //구글지도 객체 참조 변수
    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //xml 참조
        FragmentManager fragmentManager= getSupportFragmentManager();
        SupportMapFragment mapFragment= (SupportMapFragment)fragmentManager.findFragmentById(R.id.map);

        //맵 데이터 읽어 오도록 [비동기]
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //파라미터 전달된 구글맵이 객체임 .
                gMap= googleMap; //다른곳에서도 사용가능

                //지도의 특정좌표 줌인 이동
                LatLng seoul = new LatLng(37.562087, 127.035192);
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 16));

                MarkerOptions marker = new MarkerOptions();
                marker.title("왕십리");
                marker.snippet("미래능력 개발 교육원");
                marker.position(seoul);
//                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_flag_36)); //아이콘 이미지는 벡터이미지 안됨,. 반드시 .jpg or .png 같은 픽셀 이미지여야만 함
                marker.anchor(0.5f, 1.0f);

                gMap.addMarker(marker);

                //지도 대표 설정들
                UiSettings settings= gMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

                //현재 내위치 탐색을 지도 라이브러리에서 제공해줌 .
                settings.setMyLocationButtonEnabled(true);

                //내 위치를 요구해야 버튼 보여짐, 동적 퍼미션 되어야 있어야함 .
                if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gMap.setMyLocationEnabled(true);

                //나머지 관련 내용을 개발자 사이트의 가이드를 참고해서 시도해보기 ~~~
            }
        });





        //동적 퍼미션 작업 .[했었는데 또해 ?]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                String[] permissions= new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 0);
            }
        }
    }//on

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
//                if (grantResults[0] ==PackageManager.PERMISSION_DENIED ||
//                        grantResults[1] ==PackageManager.PERMISSION_DENIED){
//                    Toast.makeText(this, "위치 사용 불가", Toast.LENGTH_SHORT).show();
//                }
                for (int i=0; i<grantResults.length; i++){
                    if (grantResults[i]==PackageManager.PERMISSION_DENIED){
                        Toast.makeText(this, "위치 사용불가", Toast.LENGTH_SHORT).show();
                        break;
                    }else{
                        Toast.makeText(this, "위치 사용가능", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
}