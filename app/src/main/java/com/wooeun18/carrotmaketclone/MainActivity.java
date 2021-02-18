package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    FragmentManager fragmentManager;
    Fragment[] fragments= new Fragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager= getSupportFragmentManager();
        FragmentTransaction tran= fragmentManager.beginTransaction();
        fragments[0]= new Tab01();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        bnv= findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //프레그먼트 바꿀때  초기화 안되게 하는거 .
                //show / hide 중요함 .
                FragmentTransaction tran= fragmentManager.beginTransaction();
                if (fragments[0] != null ) tran.hide(fragments[0]);
                if (fragments[1] != null ) tran.hide(fragments[1]);
                if (fragments[2] != null ) tran.hide(fragments[2]);
                if (fragments[3] != null ) tran.hide(fragments[3]);
                if (fragments[4] != null ) tran.hide(fragments[4]);

                switch (item.getItemId()){
                    case R.id.tab01 :
                        tran.show(fragments[0]);
                        break;

                    case R.id.tab02 :
                        if (fragments[1] == null ){
                            fragments[1] = new Tab02();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        break;

                    case R.id.tab03 :
                        if (fragments[2] == null ){
                            fragments[2] = new Tab03();
                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        break;

                    case R.id.tab04 :
                        if (fragments[3] == null ){
                            fragments[3] = new Tab04();
                            tran.add(R.id.container, fragments[3]);
                        }
                        tran.show(fragments[3]);
                        break;

                    case R.id.tab05 :
                        if (fragments[4] == null ){
                            fragments[4] = new Tab05();
                            tran.add(R.id.container, fragments[4]);
                        }
                        tran.show(fragments[4]);
                        break;
                }
                tran.commit();

                return true;
            }
        });
    }//on
}//main



