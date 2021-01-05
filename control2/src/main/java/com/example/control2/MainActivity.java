package com.example.control2;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ImageSwitcher imageSwitcher = null;
    private float touchDownX = 0;
    private float touchUpX = 0;
    private int pictureIndex = 0;
    private final int[] imageId = new int[] {
      R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.mipmap.addbj, R.mipmap.bg, R.mipmap.ic_action_name
    };
    private List<View> viewList = new ArrayList<View>();
    private List<String> tabList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // image switcher
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(getImageSource());
                return imageView;
            }
        });
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchDownX = event.getX();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchUpX = event.getX();
                    if (touchUpX - touchDownX > 100) {
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_left));
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right));
                        imageSwitcher.setImageResource(getImageSource());
                    } else if (touchDownX - touchUpX > 100) {
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left));
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right));
                        imageSwitcher.setImageResource(getImageSource());
                    }
                    pictureIndex = pictureIndex == 0 ? 1 : 0;
                }
                return true;
            }
        });
        // gallery
//        Gallery gallery = (Gallery)findViewById(R.id.gallery);
//        gallery.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                ImageView imageView;
//                if (convertView == null) {
//                    imageView = new ImageView(MainActivity.this);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    imageView.setLayoutParams(new Gallery.LayoutParams(120, 90));
//                    TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
//                }
//                return null;
//            }
//        });

        // spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // listview
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
        // view pager
        LayoutInflater layoutInflater = getLayoutInflater().from(this);
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewList.add(layoutInflater.inflate(R.layout.layout1, null));
        viewList.add(layoutInflater.inflate(R.layout.layout2, null));
        viewList.add(layoutInflater.inflate(R.layout.layout3, null));
        viewList.add(layoutInflater.inflate(R.layout.layout4, null));

        tabList.add("title1");
        tabList.add("title2");
        tabList.add("title3");
        tabList.add("title4");
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(viewList.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabList.get(position);
            }
        });
        //
//        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);    //获取TabHost对象
//        tabHost.setup();                                            //初始化TabHost组件
//        // 声明并实例化一个LayoutInflater对象
//        LayoutInflater inflater = LayoutInflater.from(this);
//        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
//        inflater.inflate(R.layout.tab2,tabHost.getTabContentView());
//        tabHost.addTab(tabHost.newTabSpec("tab1")
//                .setIndicator("精选表情")
//                .setContent(R.id.linearlayout1));             //添加第一个标签页
//        tabHost.addTab(tabHost.newTabSpec("tab2")
//                .setIndicator("投稿表情")
//                .setContent(R.id.framelayout)) ;              //添加第二个标签页

        // search view
        SearchView searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "submit : " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "change : " + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        searchView.setSubmitButtonEnabled(true);

    }

    private int getImageSource() {
        return pictureIndex == 0 ?  imageId[0] : imageId[1];
    }


}
