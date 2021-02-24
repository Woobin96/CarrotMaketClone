package com.wooeun18.carrotmaketclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab04 extends Fragment {

    FloatingActionButton fab;

    Toolbar toolbar;

    RecyclerView recyclerView;
    Tab04Adapter adapter;
    ArrayList<Item04> items= new ArrayList<Item04>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab04, container, false);
        toolbar= view.findViewById(R.id.toolbar);
        fab= view.findViewById(R.id.fab);

        recyclerView= view.findViewById(R.id.rec);
        adapter= new Tab04Adapter();

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.tab01_menu, menu);
    }

}













