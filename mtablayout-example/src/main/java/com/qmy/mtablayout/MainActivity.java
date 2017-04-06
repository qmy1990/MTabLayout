package com.qmy.mtablayout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.qmy.mtablayoutlibrary.MTabLayout;

public class MainActivity extends AppCompatActivity {

    private MTabLayout bottomTabView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initData() {
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager_main);
        bottomTabView = (MTabLayout) findViewById(R.id.bottomTabView_main);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        bottomTabView.setViewPager(viewPager);
    }

    private void initListener() {

    }
}
