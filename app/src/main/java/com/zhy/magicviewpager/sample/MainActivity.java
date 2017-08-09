package com.zhy.magicviewpager.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStandardViewPager(View view) {
        Intent intent = new Intent(MainActivity.this, StandardViewPagerActivity.class);
        startActivity(intent);
    }

    public void onCircleViewPager(View view) {
        Intent intent = new Intent(MainActivity.this, CircleViewPagerActivity.class);
        startActivity(intent);
    }

}
