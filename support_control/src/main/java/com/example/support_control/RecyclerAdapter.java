package com.example.support_control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    //图标数组
    private int[] icons = {
            R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3,
            R.mipmap.icon_4, R.mipmap.icon_5, R.mipmap.icon_6,
            R.mipmap.icon_7, R.mipmap.icon_8, R.mipmap.icon_9,
            R.mipmap.icon_10, R.mipmap.icon_11
    };
    //名字数组，引用资源文件中的文字
    private int[] names = {
            R.string.name1, R.string.name2, R.string.name3,
            R.string.name4, R.string.name5, R.string.name6,
            R.string.name7, R.string.name8, R.string.name9,
            R.string.name10, R.string.name11
    };
    //信息数组
    private int[] infos = {
            R.string.info1, R.string.info2, R.string.info3,
            R.string.info4, R.string.info5, R.string.info6,
            R.string.info7, R.string.info8, R.string.info9,
            R.string.info10, R.string.info11
    };
    private Context lContext;   //上下文
    private List<Integer> listIcon = new ArrayList<Integer>();   //图标集合
    private List<Integer> listName = new ArrayList<Integer>();   //名称集合
    private List<Integer> listInfo = new ArrayList<Integer>();   //信息集合
    public RecyclerAdapter(Context context) {
        lContext = context;
        //设置菜单行数与行内图标、名称、信息
        for (int i = 0; i < 11; i++) {
            listIcon.add(icons[i]);
            listName.add(names[i]);
            listInfo.add(infos[i]);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        //获取列表中，每行的布局文件
        View view = LayoutInflater.from(lContext).inflate(R.layout.layout_item, arg0, false);
        MyViewHolder holder = new MyViewHolder(view);           //
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //设置图标
        holder.img.setBackgroundResource(listIcon.get(position));
        //设置名称
        holder.name.setText(listName.get(position));
        //设置信息
        holder.info.setText(listInfo.get(position));
    }

    @Override
    public int getItemCount() {
        return listIcon.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, info;              //编号文字
        public ImageView img;                     //图标

        //获取相关控件
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            info = (TextView) itemView.findViewById(R.id.info);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}

