package com.example.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(this, "<= 6.0", Toast.LENGTH_SHORT).show();

            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        } else {
            Toast.makeText(this, "> 6.0", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, permissions[i] + " GRANTED", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, permissions[i] + " DENIED", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
