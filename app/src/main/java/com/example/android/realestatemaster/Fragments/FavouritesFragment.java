package com.example.android.realestatemaster.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.realestatemaster.HelperClasses.RecyclerViewAdapter;
import com.example.android.realestatemaster.HelperClasses.RecyclerViewAdapterSmall;
import com.example.android.realestatemaster.HelperClasses.Utilities;
import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.activities.DetailActivity;
import com.example.android.realestatemaster.activities.ResultsActivity;
import com.example.android.realestatemaster.utils.GsonModel.ErrorJsonModel;
import com.example.android.realestatemaster.utils.GsonModel.Listing;
import com.example.android.realestatemaster.utils.GsonModel.ProportyJsonModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class FavouritesFragment extends Fragment{
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite,container, false);
        return view;
    }


}
