package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPhone(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:08617600232556"));
        startActivity(intent);
    }

    public void onClickMsg(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:5554"));
        intent.putExtra("sms_body", "welcome");
        startActivity(intent);
    }

    public void onClickChangeTouxiang(View view) {
        Intent intent = new Intent(MainActivity.this, HeadActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && requestCode == 1 ) {
            Bundle bundle = data.getExtras();
            int imageId = bundle.getInt("imageid");
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageResource(imageId);
        }
    }
}

