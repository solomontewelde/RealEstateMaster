package com.example.android.realestatemaster.activities;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.android.realestatemaster.Fragments.AccountFragment;
import com.example.android.realestatemaster.Fragments.FavouritesFragment;
import com.example.android.realestatemaster.Fragments.RecentFragment;
import com.example.android.realestatemaster.Fragments.SearchFragment;
import com.example.android.realestatemaster.Fragments.SettingFragment;
import com.example.android.realestatemaster.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private BottomBar bottomBar;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

   //  frameLayout = findViewById(R.id.search_parent);
        bottomBar = findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.search_navig_button: {
                        SearchFragment searchFragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.search_parent,searchFragment ).commit();
                        break;
                    }
                    case R.id.recent_navig_button: {
                        RecentFragment recentFragment = new RecentFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.search_parent, recentFragment).commit();
                        break;
                    }
                    case R.id.favourite_navig_button: {
                        FavouritesFragment favouriteFragment = new FavouritesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.search_parent, favouriteFragment).commit();
                        break;
                    }
                    case R.id.setting_navig_button: {
                       // frameLayout.removeAllViews();//this is used to remove a frame layout
                        SettingFragment settingFragment = new SettingFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.search_parent, settingFragment).commit();
                        break;
                    }
                    case R.id.user_account_navig_button: {
                        AccountFragment accountFragment = new AccountFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.search_parent, accountFragment).commit();
                        break;
                    }

                }


            }


        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

            }
        });

    }



}
