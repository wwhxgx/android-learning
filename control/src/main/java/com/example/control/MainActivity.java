package com.example.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.nio.DoubleBuffer;
import java.util.Random;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.in;

public class MainActivity extends Activity {

    private final Integer[] pictures = {
            R.mipmap.ic_launcher,  R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
    };

    private final String[] testStrs = new String[] {
            "test1", "test2", "test3",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 务必需要设置全屏展示，不然输入法会遮挡部分窗口
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // grid view
        final GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));
        // auto complete text view
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, testStrs));
        //text switcher
        TextSwitcher textSwitcher = (TextSwitcher)findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(30);
                textView.setTextColor(Color.GREEN);
                return textView;
            }
        });
        // switch
        final Switch switcher = (Switch)findViewById(R.id.switcher);
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "switch open", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "switch closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //raido group
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)findViewById(checkedId);
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    RadioButton radioButton1 = (RadioButton)group.getChildAt(i);
                    if (radioButton != radioButton1) {
                        radioButton1.setChecked(false);
                    }
                }
            }
        });
        // seekbar
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void onClickButton(View view) {
        Toast.makeText(this, "click button", Toast.LENGTH_SHORT).show();
    }

    public void onClickImageButton(View view) {
        Toast.makeText(this, "click image button", Toast.LENGTH_SHORT).show();
    }

    public void onClickImageView(View view) {
        Toast.makeText(this, "click image view", Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClick(View view) {
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        for (int i = 0; i < radioGroup.getChildCount(); i++){
            RadioButton radioButton = (RadioButton)radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            TextSwitcher textSwitcher = (TextSwitcher)findViewById(R.id.textSwitcher);
            int max = testStrs.length - 1,min = 0;
            long randomNum = System.currentTimeMillis();
            int ran3 = (int) (randomNum%(max-min)+min);
            textSwitcher.setText(testStrs[ran3]);
        }
        return super.onTouchEvent(event);
    }

    //image adapter
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        public ImageAdapter(Context c) {
            super();
            mContext = c;
        }

        @Override
        public int getCount() {
            return pictures.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(100, 90));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView)convertView;
            }
            imageView.setImageResource(pictures[position]);
            return imageView;
        }
    }
}


