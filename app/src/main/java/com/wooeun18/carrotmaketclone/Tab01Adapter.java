package com.wooeun18.carrotmaketclone;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Tab01Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<FAB01> items;

    public Tab01Adapter() {
    }

    public Tab01Adapter(Context context, ArrayList<FAB01> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);

        View itenview= inflater.inflate(R.layout.item01, parent , false);
        VH holder= new VH(itenview);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;

        FAB01 item01= items.get(position);
        vh.tvTitle.setText(item01.title);
        vh.tvM.setText(item01.money + "원");

        //String 였음
        String iv= item01.img;
        Glide.with(context).load(iv).into(vh.tviv);
//        Log.i("AAA",iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView tviv;
        TextView tvTitle;
        TextView tvM;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvTitle= itemView.findViewById(R.id.tv_Title);
            tvM= itemView.findViewById(R.id.tv_Money);
            tviv= itemView.findViewById(R.id.iv);

            //이미지 null 값 나옴
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int poi= getAdapterPosition();

                    String img= items.get(poi).img;
                    String title= items.get(poi).title;
                    String msg= items.get(poi).msg;
                    String money= items.get(poi).money;

                    Intent intent= new Intent(context, Tab01DetailsActivity.class);
                    intent.putExtra("img", img);
                    intent.putExtra("title", title);
                    intent.putExtra("msg", msg);
                    intent.putExtra("money", money);

                    context.startActivity(intent);

                    String aaa= items.get(poi).img;
//                    Toast.makeText(context, ""+aaa, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}//class





















