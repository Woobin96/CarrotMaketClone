package com.wooeun18.carrotmaketclone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tab02Adapter extends RecyclerView.Adapter<Tab02Adapter.VH> {

    Context context;
    ArrayList<Item02> items;

    public Tab02Adapter(Context context, ArrayList<Item02> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemviewe= inflater.inflate(R.layout.item02,parent, false);
        VH vh= new VH(itemviewe);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        Item02 item= items.get(position);

        //이미지 설정 [DB에는 이미지경로가 "./uploads/IMG_20210240_moana01.jpg" 임]
        //안드로이드에서는 서버(닷홈)의 전체주소가 필요하기에
        String imgUrl= "http://binwoo.dothome.co.kr/Bluemaket/" + item.img;
//        Log.i("Log", imgUrl);
        Glide.with(context).load(imgUrl).into(holder.iv);

        holder.tvMsg.setText(item.msg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //뷰홀더 이너클래스
    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        CircleImageView civ;
        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            civ= itemView.findViewById(R.id.civ);
            iv= itemView.findViewById(R.id.iv_msg);
            tvMsg= itemView.findViewById(R.id.tv_msg);

        }
    }
}

