package com.example.administrator.fragmenttablehost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.fragmenttablehost.Adapter.LeadImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.viewpager)ViewPager mViewPager;
    private ImageView[] points = new ImageView[3];
    private LeadImgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = getSharedPreferences("isFirst",MODE_PRIVATE);
        //从文件中获取存储的数据，默认为true
        boolean isFirst = preferences.getBoolean("isFirst",true);
        if (!isFirst){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        points[0]=(ImageView) findViewById(R.id.iv_p1);
        points[1]=(ImageView) findViewById(R.id.iv_p2);
        points[2]=(ImageView) findViewById(R.id.iv_p3);
        setPoint(0);
        //设置每一个具体界面的样式
        List<View> viewList = new ArrayList<>();
        viewList.add(getLayoutInflater().inflate(R.layout.lead_1,null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_2,null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_3,null));
        //初始化适配器
        mAdapter = new LeadImgAdapter(viewList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(listener);
    }

    private void setPoint(int index){
        for (int i = 0; i < points.length; i++) {
            if (i == index){
                points[i].setAlpha(255);
            }else {
                points[i].setAlpha(100);
            }
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        /**界面切换时调用*/
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**当界面切换后调用*/
        @Override
        public void onPageSelected(int position) {

            setPoint(position);
            if (position >= 2){
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                SharedPreferences preferences = getSharedPreferences("isFirst",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirst",false);
                editor.commit();
            }

        }

        /**滑动状态变化时调用*/
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
}
