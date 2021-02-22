package com.wooeun18.carrotmaketclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tab01 extends Fragment {

    FloatingActionButton fab;

    RecyclerView recyclerView;
    Tab01Adapter adapter;
    ArrayList<FAB01> items= new ArrayList<FAB01>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab01, container, false);

        Toolbar toolbar= view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        fab= view.findViewById(R.id.fab);

        recyclerView= view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true); //사이즈 맞추는거 .

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter= new Tab01Adapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        //+ 버튼
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FABActivity.class);
                startActivity(intent);
            }
        });

        //Firebase 실시간 데이터베이스 관리객체 얻어오기 [서버연결]
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();

        //데이터베이스의 최상위(root) 노드 참조객체 가져오기 .
        DatabaseReference rootRef= firebaseDatabase.getReference();
        rootRef.child("FAB01").addChildEventListener(new ChildEventListener() { // ChildEventListener를 써야 중복되지 않고 나옴
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    FAB01 item01 = snapshot.getValue(FAB01.class);
                Log.i("AAA", item01.img);

                    items.add(0,item01);
                    adapter.notifyItemInserted(items.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        rootRef.child("FAB01").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot snap : snapshot.getChildren()){
//                    Item01 item01 = snap.getValue(Item01.class);
//
//                    items.add(0,item01);
//                    adapter.notifyItemInserted(items.size());
//
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return view;
    }//onCreate

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
            case R.id.tab01_2 :
                Intent intent= new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
