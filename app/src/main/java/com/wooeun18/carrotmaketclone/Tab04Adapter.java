package com.wooeun18.carrotmaketclone;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class Tab04Adapter extends RecyclerView.Adapter<Tab04Adapter.VH> {

    Context context;
    ArrayList<Item04> items;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemviewe= inflater.inflate(R.layout.item04, parent, false);
        VH vh= new VH(itemviewe);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
//        return items.size();
        return 0;
    }

    //뷰홀더 이너클래스
    class VH extends RecyclerView.ViewHolder{

        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvMsg= itemView.findViewById(R.id.tv_msg);

        }
    }
}

