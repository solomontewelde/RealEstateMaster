package com.example.android.realestatemaster.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.realestatemaster.R;



public class SearchFragment extends Fragment {
    private FragmentTabHost fragmentTabHost;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        fragmentTabHost = view.findViewById(android.R.id.tabhost);//Remember this is anroid.R.id....and is declared by android:id = "@android:id ="" ..in layout file
        fragmentTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("rent").setIndicator("RENT"),RentTabFragment.class,null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("buy").setIndicator("BUY"),BuyTabFragment.class,null);
        return view;
    }

}
