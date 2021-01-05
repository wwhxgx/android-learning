package com.example.support_control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<String> lists;                //文字信息
    private Context context;                   //定义上下文
    private List<Integer> heights;             //网格随机高度
    private OnItemClickListener mListener;     //单击事件接口
    private List<Integer> resids;              //图片id

    //构造方法
    public MyRecyclerAdapter(Context context, List<String> lists, List<Integer> resids) {
        this.context = context;
        this.lists = lists;
        this.resids = resids;
        getRandomHeight(this.lists);
    }

    //得到随机item的高度
    private void getRandomHeight(List<String> lists) {
        heights = new ArrayList<>();                         //网格高度的集合
        for (int i = 0; i < lists.size(); i++) {
            //随机产生网格高度并添加到集合中
            heights.add((int) (200 + Math.random() * 100));
        }
    }
    //单击事件与长按事件的接口
    public interface OnItemClickListener {
        void ItemClickListener(View view, int postion);
        void ItemLongClickListener(View view, int postion);
    }
    //设置单击事件的方法
    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载网格中的布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //得到item的LayoutParams布局参数
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = heights.get(position);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);//把params设置给item布局
        holder.mIv.setBackgroundResource(resids.get(position % 15));//设置图片
        holder.mTv.setText("图书" + lists.get(position));//为控件绑定数据
        if (mListener != null) {//如果设置了监听那么它就不为空，然后回调相应的方法
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
                    // 把事件交给我们实现的接口那里处理
                    mListener.ItemClickListener(holder.itemView, pos);
//                    Toast.makeText(context, "单击了图书：" + Integer.toString(pos),Toast.LENGTH_SHORT).show();
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
                    //把事件交给我们实现的接口那里处理
                    mListener.ItemLongClickListener(holder.itemView, pos);
                    return true;
                }
            });
        }
    }


    /**
     * 返回数据集中的项目总数
     */
    @Override
    public int getItemCount() {
        return lists.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;           //显示图片标题文字的组件
        ImageView mIv;          //显示图片的组件

        public MyViewHolder(View itemView) {
            super(itemView);
            //获取显示图片标题文字的组件
            mTv = (TextView) itemView.findViewById(R.id.textView);
            //获取显示图片的组件
            mIv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}


