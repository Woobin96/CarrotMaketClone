package com.wooeun18.carrotmaketclone;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class Tab02 extends Fragment {

    ArrayList<Item02> items =new ArrayList<Item02>();

    FloatingActionButton fab;

    RecyclerView rec;
    Tab02Adapter adapter;
    SwipeRefreshLayout refreshLayout;
    CircleImageView civ;

    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab02, container, false);

        toolbar= view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        fab= view.findViewById(R.id.fab);

        civ= view.findViewById(R.id.civ);
        rec= view.findViewById(R.id.rec);
        adapter= new Tab02Adapter(getActivity(), items);
        rec.setAdapter(adapter);
        refreshLayout= view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });

        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(getActivity(), permissions[0]) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }

        //+ 버튼
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FAB02Activity.class);
                startActivity(intent);
            }
        });


        return view;

    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.tab02_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();
        switch (id){
            case R.id.tab02_1 :
                break;
            case R.id.tab02_2:
                Intent intent= new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    } //메뉴 클릭

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
    void loadData(){

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<Item02>> call= retrofitService.loadDataFromService();
        call.enqueue(new Callback<ArrayList<Item02>>() {
            @Override
            public void onResponse(Call<ArrayList<Item02>> call, Response<ArrayList<Item02>> response) {
                //기존 데이터들 모두 제거
                items.clear();
                adapter.notifyDataSetChanged();

                ArrayList<Item02> list= response.body();
                for(Item02 item : list){
                    items.add(0, item);
                    adapter.notifyItemInserted(0);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Item02>> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }//load

}













