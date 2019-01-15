package com.example.android.realestatemaster.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.realestatemaster.R;

import java.util.ArrayList;

/**
 * Created by 100043392 on 10-Apr-18.
 */

public class RecentFragment extends Fragment{
TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent,container, false);
        textView = view.findViewById(R.id.search_test);
        SharedPreferences preferences = getActivity().getSharedPreferences("RECENT_SEARCHES", Context.MODE_PRIVATE);
        String history = preferences.getString("s","No data");
        Log.d("history",history);
        if(history !="" && history!=null){
            textView.setText(history);
        }
        return view;
    }
}
