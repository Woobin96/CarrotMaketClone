package com.wooeun18.carrotmaketclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager= getSupportFragmentManager();
        FragmentTransaction tran= fragmentManager.beginTransaction();
        tran.add(R.id.container, new Tab01());
        tran.commit();

        bnv= findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran= fragmentManager.beginTransaction();
                Fragment fragment= null;

                switch (item.getItemId()){
                    case R.id.tab01 :
                        break;
                }

                return true;
            }
        });
    }//on
}//main




















