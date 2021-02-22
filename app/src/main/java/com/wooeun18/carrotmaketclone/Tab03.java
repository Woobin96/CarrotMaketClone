package com.wooeun18.carrotmaketclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab03 extends Fragment {

    ArrayList<Tab03RecyclerItem> items= new ArrayList<Tab03RecyclerItem>();
    RecyclerView recycler;
    Tab03Adapter adapter;

    ArrayList<Tab03RecyclerItem> items2= new ArrayList<Tab03RecyclerItem>();
    RecyclerView recycler2;
    Tab03Adapter adapter2;

    ArrayList<Tab03RecyclerItem> items3= new ArrayList<Tab03RecyclerItem>();
    RecyclerView recycler3;
    Tab03Adapter adapter3;

    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab03, container, false);
        toolbar= view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        recycler= view.findViewById(R.id.recycler);
        adapter= new Tab03Adapter(getActivity(), items);
        recycler.setAdapter(adapter);

        recycler2= view.findViewById(R.id.recycler2);
        adapter2= new Tab03Adapter(getActivity(), items2);
        recycler2.setAdapter(adapter2);

        recycler3= view.findViewById(R.id.recycler3);
        adapter3= new Tab03Adapter(getActivity(), items3);
        recycler3.setAdapter(adapter3);

        loadData(); //이미지 설정 보이는거

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.tab03, menu);
    }

    public void loadData(){

        items.add(new Tab03RecyclerItem(R.drawable.img01));
        items.add(new Tab03RecyclerItem(R.drawable.img02));
        items.add(new Tab03RecyclerItem(R.drawable.img03));
        items.add(new Tab03RecyclerItem(R.drawable.img04));
        items.add(new Tab03RecyclerItem(R.drawable.img05));

        items2.add(new Tab03RecyclerItem(R.drawable.img06));
        items2.add(new Tab03RecyclerItem(R.drawable.img07));
        items2.add(new Tab03RecyclerItem(R.drawable.img08));
        items2.add(new Tab03RecyclerItem(R.drawable.img09));

        items3.add(new Tab03RecyclerItem(R.drawable.img10));
        items3.add(new Tab03RecyclerItem(R.drawable.img11));
        items3.add(new Tab03RecyclerItem(R.drawable.img12));
        items3.add(new Tab03RecyclerItem(R.drawable.img03));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();
        switch (id){
            case R.id.tab01_1 :
                break;
            case R.id.tab01_2:
                Intent intent= new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}













