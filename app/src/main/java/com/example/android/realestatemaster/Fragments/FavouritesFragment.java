package com.example.android.realestatemaster.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.realestatemaster.HelperClasses.RecyclerViewAdapter;
import com.example.android.realestatemaster.HelperClasses.RecyclerViewAdapterFavorites;
import com.example.android.realestatemaster.HelperClasses.Utilities;
import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.activities.PropertyDetailsActivity;
import com.example.android.realestatemaster.activities.SearchResultsActivity;
import com.example.android.realestatemaster.utils.GsonModel.ErrorJsonModel;
import com.example.android.realestatemaster.utils.GsonModel.Listing;
import com.example.android.realestatemaster.utils.GsonModel.ProportyJsonModel;
import com.example.android.realestatemaster.utils.SearchQuerryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FavouritesFragment extends Fragment implements RecyclerViewAdapterFavorites.OnItemClickListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerViewAdapterFavorites recyclerViewAdapterFavorites;
    private String jsonResponse;
    private ErrorJsonModel errorData;
    private ProportyJsonModel propertyData;
    private ArrayList<Listing> propertyListings;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.favorites_recycler_view_id);
        progressBar = view.findViewById(R.id.progress_id);
        progressBar.setVisibility(View.INVISIBLE);
        new FetchDataFromSharedPreference().execute();
        return view;
    }


    @Override
    public void onItemClick(int clickedItemIndex) {
        String queryString = new SearchQuerryBuilder().queryByListingId(propertyListings.get(clickedItemIndex).getListingId());
        if (Utilities.isWifiEnabled(getActivity().getApplicationContext())){
            new FetchJson().execute(queryString);

        }
 }

    public class FetchJson extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient okHttpClient = new OkHttpClient();
            String queryString = params[0];
            Request request = new Request.Builder().url(queryString).build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                jsonResponse = response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.INVISIBLE);
            Gson gson = new Gson();
            ProportyJsonModel proportyJsonModel = null;
            ErrorJsonModel errorJsonModelModel = null;
            try {
                proportyJsonModel = gson.fromJson(jsonResponse, ProportyJsonModel.class);
                propertyData = proportyJsonModel;
                errorJsonModelModel = gson.fromJson(jsonResponse, ErrorJsonModel.class);
                errorData = errorJsonModelModel;
            } catch (IllegalStateException | JsonSyntaxException exception) {
                Log.e("ConectionError", exception.getLocalizedMessage());

            }
            if (errorData != null) {
                if (errorData.getErrorString() != null) {
                    Log.e("Elor", errorData.getErrorString());
                    Toast.makeText(getActivity().getApplicationContext(), errorData.getErrorString(), Toast.LENGTH_SHORT).show();
                }
                Log.e("Error data", jsonResponse);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Error connecting to server", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getActivity().getApplicationContext(),PropertyDetailsActivity.class);
            Listing clickedListing = propertyData.getListing().get(0);//because the result should have one item which was queried by id
            intent.putExtra("LISTING_DETAILS",clickedListing);
            startActivity(intent);

        }

    }
    public class FetchDataFromSharedPreference extends AsyncTask<Void,Void,ArrayList<Listing>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Listing> doInBackground(Void... voids) {
          ArrayList<Listing>  listings = new ArrayList<>();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            Map<String, ?> keys = sharedPreferences.getAll();
            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                Log.d("map values", entry.getKey() + ": " +
                        entry.getValue().toString());
                Gson gson = new Gson();
                Listing listing = gson.fromJson(entry.getValue().toString(), Listing.class);
                listings.add(listing);
            }
            return listings;
        }

        @Override
        protected void onPostExecute(ArrayList<Listing> listings) {
            super.onPostExecute(listings);
            progressBar.setVisibility(View.INVISIBLE);
            recyclerViewAdapterFavorites = new RecyclerViewAdapterFavorites(getActivity().getApplicationContext(), listings);
            recyclerViewAdapterFavorites.setOnItemClickListener(FavouritesFragment.this);
            recyclerView.setAdapter(recyclerViewAdapterFavorites);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            propertyListings = listings;



        }
    }
}
