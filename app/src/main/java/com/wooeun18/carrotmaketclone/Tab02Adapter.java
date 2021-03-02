package com.wooeun18.carrotmaketclone;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class Tab02Adapter extends RecyclerView.Adapter<Tab02Adapter.VH> {

    Context context;
    ArrayList<Item02> items;
    CircleImageView civ;
    TextView tvName;

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

        civ= itemviewe.findViewById(R.id.civ);
        tvName= itemviewe.findViewById(R.id.name);



        loadDB();
        if (G.profileUrl!=null) {
            tvName.setText(G.nickName);
            Picasso.get().load(G.profileUrl).into(civ);
        }

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
//        holder.tvdate.setText(item.data);
    }

    void loadDB(){
        SharedPreferences pref= context.getSharedPreferences("account", MODE_PRIVATE);
        G.nickName= pref.getString("nickName", null);
        G.profileUrl= pref.getString("profileUrl", null);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //뷰홀더 이너클래스
    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        CircleImageView civ;
        TextView tvMsg, tvdate;

        public VH(@NonNull View itemView) {
            super(itemView);

            civ= itemView.findViewById(R.id.civ);
            iv= itemView.findViewById(R.id.iv_msg);
            tvMsg= itemView.findViewById(R.id.tv_msg);
            tvdate= itemView.findViewById(R.id.tv_date);

//            Toast.makeText(context, ""+tvdate, Toast.LENGTH_SHORT).show();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int poi= getAdapterPosition();

                    String civ= items.get(poi).civ;
                    String name= items.get(poi).name;
                    String msg= items.get(poi).msg;
                    String img= items.get(poi).img;
                    String date= items.get(poi).data;

                    Intent intent= new Intent(context, Tab02DetailsActivity.class);
                    intent.putExtra("civ", civ);
                    intent.putExtra("name", name);
                    intent.putExtra("msg", msg);
                    intent.putExtra("img", img);
                    intent.putExtra("date", date);

                    //화면 애니메이션 이거밖에 못하겠음 .
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context, new Pair<View, String>(iv, "img"));
                        context.startActivity(intent, options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

//                    context.startActivity(intent);

                }
            });

        }
    }
}

