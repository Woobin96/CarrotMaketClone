package com.wooeun18.carrotmaketclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab01Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Item01> items;

    public Tab01Adapter() {
    }

    public Tab01Adapter(Context context, ArrayList<Item01> items) {
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

        Item01 item01= items.get(position);
        vh.tvTitle.setText(item01.title);
        vh.tvM.setText(item01.money);

        ((VH) holder).tvM.setText(item01.money+ "원");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvTitle;
        TextView tvM;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvTitle= itemView.findViewById(R.id.tv_Title);
            tvM= itemView.findViewById(R.id.tv_Money);
            iv= itemView.findViewById(R.id.iv);
        }
    }
}//class





















