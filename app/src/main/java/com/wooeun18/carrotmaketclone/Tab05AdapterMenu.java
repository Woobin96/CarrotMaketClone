package com.wooeun18.carrotmaketclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tab05AdapterMenu extends BaseAdapter {

    Context context;
    ArrayList<Item05> items;

    public Tab05AdapterMenu(Context context, ArrayList<Item05> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
//            LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);

            LayoutInflater inflater= LayoutInflater.from(context);
            convertView= inflater.inflate(R.layout.listview_item, null);
        }


        Item05 item= items.get(position);
        ImageView imgs= convertView.findViewById(R.id.imges);
        TextView tv= convertView.findViewById(R.id.tv_munu);

        imgs.setImageResource(item.imgs);
        tv.setText(item.menu);

        return convertView;
    }
}









