package com.wooeun18.carrotmaketclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Tab02 extends Fragment {

    FloatingActionButton fab;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab02, container, false);
        Toolbar toolbar= view.findViewById(R.id.toolbar);
        fab= view.findViewById(R.id.fab);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.tab01_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();
        switch (id){
            case R.id.tab01_1 :
                break;
            case R.id.tab01_2:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}













