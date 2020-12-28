package com.example.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtion1(View view) {
        startActivity(new Intent(MainActivity.this, RelativeActivity.class));
    }

    public void onClickButtion2(View view) {
        startActivity(new Intent(MainActivity.this, LinearActivity.class));
    }

    public void onClickButtion3(View view) {
        startActivity(new Intent(MainActivity.this, FrameActivity.class));
    }

    public void onClickButtion4(View view) {
        startActivity(new Intent(MainActivity.this, TableActivity.class));
    }

    public void onClickButtion5(View view) {
        startActivity(new Intent(MainActivity.this, GridActivity.class));
    }
}
