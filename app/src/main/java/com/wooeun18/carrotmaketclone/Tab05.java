package com.wooeun18.carrotmaketclone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class Tab05 extends Fragment {

    ArrayList<Item05> items= new ArrayList<Item05>();
    Tab05AdapterMenu adapter;
    ListView listView;

    Toolbar toolbar;

    CircleImageView civ;
    TextView tvName;
    Button btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab05, container, false);
        toolbar= view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        listView= view.findViewById(R.id.listView);
        adapter= new Tab05AdapterMenu(getActivity(), items);

        civ= view.findViewById(R.id.civ);
        tvName= view.findViewById(R.id.tv_name);
        btn= view.findViewById(R.id.btn);

        items.add(new Item05("내 동네 설정", R.drawable.ic_place_24));
        items.add(new Item05("동네 인증하기", R.drawable.ic_fixed_24));
        items.add(new Item05("키워드 알림", R.drawable.ic_loyalty_24));
        items.add(new Item05("모아보기", R.drawable.ic_list_24));
        items.add(new Item05("동네생활 글", R.drawable.ic_long_24));
        items.add(new Item05("친구 초대", R.drawable.ic_near_me_24));
        items.add(new Item05("공유", R.drawable.ic_plot_24));
        items.add(new Item05("공지사항", R.drawable.ic_mic_24));
        items.add(new Item05("앱 설정", R.drawable.ic_settings_24));

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item05 item= items.get(position);
                if(position == 0 || position == 1){
                    Intent intent= new Intent(getActivity(),MapActivity.class);
                    startActivity(intent);
                }

                Toast.makeText(getActivity(), item.menu+"\nComing soon", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        loadDB();
        if (G.nickName != null){
            tvName.setText(G.nickName);
            Picasso.get().load(G.profileUrl).into(civ);
        }


        return view;
    }

    public void loadDB(){
        SharedPreferences pref= getActivity().getSharedPreferences("account", MODE_PRIVATE);
        G.nickName= pref.getString("nickName", null);
        G.profileUrl= pref.getString("profileUrl", null);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.tab05, menu);
    }



}













