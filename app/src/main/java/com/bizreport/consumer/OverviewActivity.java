package com.bizreport.consumer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bizreport.consumer.adapters.ViewPagerAdapter;
import com.bizreport.consumer.database.Company;

import org.greenrobot.eventbus.EventBus;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        ViewPager viewPager = (ViewPager)findViewById(R.id.container);
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), EventBus.getDefault().removeStickyEvent(Company.class)));
        tabs.setupWithViewPager(viewPager);
    }

}
