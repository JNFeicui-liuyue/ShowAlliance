package com.example.administrator.fragmenttablehost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.fragmenttablehost.fragment.Alliancefragment;
import com.example.administrator.fragmenttablehost.fragment.DiscoveryFragment;
import com.example.administrator.fragmenttablehost.fragment.InfoFragment;
import com.example.administrator.fragmenttablehost.fragment.MineFragment;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    Fragment fragment;

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        //启动fragment
        mFragments = new LinkedList<>();
        fragment = new DiscoveryFragment();
        mFragments.add(fragment);

        fragment = new Alliancefragment();
        mFragments.add(fragment);

        fragment = new InfoFragment();
        mFragments.add(fragment);

        fragment = new MineFragment();
        mFragments.add(fragment);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment_container, mFragments.get(0));
        transaction.commit();

        //RadioButton切换fragment
        RadioGroup tabBar = (RadioGroup) findViewById(R.id.main_tab_bar);
        tabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                FragmentManager manager1 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction1 = manager1.beginTransaction();
                int index = 0;
                switch (checkedId) {
                    case R.id.main_tab_discovery:
                        index = 0;
                        break;
                    case R.id.main_tab_alliance:
                        index = 1;
                        break;
                    case R.id.main_tab_info:
                        index = 2;
                        break;
                    case R.id.main_tab_mine:
                        index = 3;
                        break;
                }

                //显示
                fragment = mFragments.get(index);
                transaction1.replace(R.id.main_fragment_container, fragment);
                transaction1.commit();

            }

        });

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

}
