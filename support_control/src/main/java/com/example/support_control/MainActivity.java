package com.example.support_control;

import android.Manifest;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String[] items = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5", "menu6", "menu7", "menu8", "menu9", "menu10", "menu11", "menu12"};
    private List<String> data = new ArrayList<String>(Arrays.asList("menu1", "menu2", "menu3", "menu4"));
    private ArrayAdapter<String> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            data.clear();
            data.addAll(Arrays.asList("menu5", "menu6", "menu7", "menu8", "menu9", "menu10", "menu11", "menu12"));
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };
    private RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
    private List<Integer> resids;             //图片id集合
    private List<String> lists;                //文字信息集合
    private MyRecyclerAdapter myRecyclerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);

        // toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        toolbar.setTitle("toolbar");
        toolbar.setTitleTextColor(Color.RED);
        toolbar.setSubtitle("subtitle");
        toolbar.setSubtitleTextColor(Color.GREEN);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Toast.makeText(MainActivity.this, id + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu1:
                        Toast.makeText(MainActivity.this, "menu1 clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        Toast.makeText(MainActivity.this, "menu2 clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        // cardview
        CardView cardView = (CardView)findViewById(R.id.cardview);
        cardView.setRadius(10);
        cardView.setCardElevation(10);
        cardView.setContentPadding(5,5,5,5);

        // drawer layout
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, result + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(MainActivity.this, "抽屉菜单打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(MainActivity.this, "抽屉菜单关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        // swipe refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(runnable, 2000);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_purple
                );
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
        ListView listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        // recyclerView
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        // grid
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.recycleView2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView1.setLayoutManager(gridLayoutManager);
        recyclerView1.setAdapter(recyclerAdapter);

        // 瀑布视图
        initData();        //加载图片与图片文字信息
        //列表控件
        RecyclerView mRecyclerView3 = (RecyclerView) this.findViewById(R.id.recycleView3);
        mRecyclerView3.setItemAnimator(new DefaultItemAnimator());
        //设置RecyclerView布局管理器为2列垂直排布
        mRecyclerView3.setLayoutManager(new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL));
        //创建适配器对象
        myRecyclerAdapter = new MyRecyclerAdapter(this,lists,resids);
        mRecyclerView3.setAdapter(myRecyclerAdapter);      //设置适配器
        myRecyclerAdapter.setOnClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            //单击事件
            @Override
            public void ItemClickListener(View view, int postion) {
                Toast.makeText(MainActivity.this,"单击了图书：" + postion,Toast.LENGTH_SHORT).show();
            }
            //长按事件
            @Override
            public void ItemLongClickListener(View view, int postion) {
                //长按删除
                lists.remove(postion);
                //在适配器中移除
                myRecyclerAdapter.notifyItemRemoved(postion);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void initData() {
        //文字信息集合
        lists = new ArrayList();
        //资源id集合
        resids= new ArrayList();
        for (int i = 0; i < 100; i++) {
            lists.add("" + i);
        }
        //添加图片id
        resids.add(R.mipmap.book0);
        resids.add(R.mipmap.book1);
        resids.add(R.mipmap.book2);
        resids.add(R.mipmap.book3);
        resids.add(R.mipmap.book4);
        resids.add(R.mipmap.book5);
        resids.add(R.mipmap.book6);
        resids.add(R.mipmap.book7);
        resids.add(R.mipmap.book8);
        resids.add(R.mipmap.book9);
        resids.add(R.mipmap.book10);
        resids.add(R.mipmap.book11);
        resids.add(R.mipmap.book12);
        resids.add(R.mipmap.book13);
        resids.add(R.mipmap.book14);
    }
}
