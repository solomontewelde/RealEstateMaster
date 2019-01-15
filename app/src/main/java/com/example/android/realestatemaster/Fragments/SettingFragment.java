package com.example.android.realestatemaster.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.activities.MainActivity;
import com.example.android.realestatemaster.activities.ResultsActivity;

/**
 * Created by 100043392 on 10-Apr-18.
 */

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container, false);
        return view;
    }
}
