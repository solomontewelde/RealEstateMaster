package com.example.android.realestatemaster.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.realestatemaster.HelperClasses.RecyclerViewAdapter;
import com.example.android.realestatemaster.HelperClasses.Utilities;
import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.utils.GsonModel.ErrorJsonModel;
import com.example.android.realestatemaster.utils.GsonModel.Listing;
import com.example.android.realestatemaster.utils.GsonModel.ProportyJsonModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener,Serializable, SwipeRefreshLayout.OnRefreshListener, RecyclerViewAdapter.OnFavoriteClickListener  {

    private ProgressBar progressBar;
    private String searchString;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ProportyJsonModel propertyData;
    private ErrorJsonModel errorData;
    private MaterialSearchView searchView;
    private Toolbar toolbar;
    private TextView errorDisplayTv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private final String LIKED_PROPERTIES_KEY = "LIKED_PROPERTIES";
    private ImageView favoriteButton;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private SharedPreferences sharedPreferences;
    private String jsonResponse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recycler_view_id);
        progressBar =findViewById(R.id.progress_id);
        errorDisplayTv = findViewById(R.id.errorTextView);
        progressBar.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.toolbar);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        favoriteButton = findViewById(R.id.favourite_fab);
        searchView = findViewById(R.id.search_view);//TODO there is another search_view id!!!


        sharedPreferences = getSharedPreferences(LIKED_PROPERTIES_KEY,0);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
              getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                //TODO this piece of code is repeated.. tidy it up!
                if (Utilities.isWifiEnabled(getApplicationContext())){
                    new FetchJson().execute(searchString);
                }
            }
        });

        Intent intent = getIntent();//Todo i think i need its necessary but i can use this.recreate() whenever applicable

        searchString = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (Utilities.isWifiEnabled(getApplicationContext())){
            new FetchJson().execute(searchString);
            Log.wtf("search",searchString);
        }

    }


    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
      this.recreate();
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item  = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        SubMenu subMenu = menu.addSubMenu(0,Menu.FIRST,Menu.NONE,"Work Bitch");
        subMenu.add(0, 10, Menu.NONE, "Get Last 5 Packets");
        subMenu.add(0, 15, Menu.NONE, "Get Last 10 Packets");
        subMenu.add(0, 20, Menu.NONE, "Get Last 20 Packets");
        getMenuInflater().inflate(R.menu.menu_search, subMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItem = item.getItemId();
        switch (selectedItem){
            case android.R.id.home:{
                onBackPressed();
                break;
            }
            case R.id.action_sort:{
               // int selectedSubmenu = item.getSubMenu().getItem().getItemId();
              /*  if (selectedSubmenu == R.id.item1){
                    Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show();
                }*/
                /*else{
                    Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();
                }*/
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRefresh() {
        if (Utilities.isWifiEnabled(getApplicationContext())){
            new FetchJson().execute(searchString);
        }
    }



    public class FetchJson extends AsyncTask<String, Void, String>{


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(true);
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
            proportyJsonModel = gson.fromJson(jsonResponse,ProportyJsonModel.class);
            propertyData = proportyJsonModel;
            errorJsonModelModel = gson.fromJson(jsonResponse, ErrorJsonModel.class);
            errorData = errorJsonModelModel;
        }
        catch (IllegalStateException|JsonSyntaxException exception){
            Log.e("ConectionError", exception.getLocalizedMessage());

        }
        swipeRefreshLayout.setRefreshing(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(getApplicationContext(),proportyJsonModel,searchString);
        adapter.setOnItemClickListener(SearchResultsActivity.this);
        adapter.setOnFavoriteClickListener(SearchResultsActivity.this,favoriteButton);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (propertyData !=null){
            if (propertyData.getListing() !=null){
               Utilities.showToast(getApplicationContext()," showing result for "+String.valueOf(propertyData.getListing().size())+" properties");
            }
        }
        if (errorData !=null){//there must be a connection error for this to be null... i think... otherwise it has either a correct or malformed data
            if (errorData.getErrorString()!=null){//I am not even sure if there could be error with no error string...maybe if the mismached modele is still assigned to the ErrorJsonModel
                Log.e("Elor",errorData.getErrorString());
                errorDisplayTv.setText(errorData.getErrorString());
                errorDisplayTv.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),errorData.getErrorString(), Toast.LENGTH_SHORT).show();
            }
            Log.e("ERER",jsonResponse);
        }
        else {
            Toast.makeText(getApplicationContext(),"Error connecting to server", Toast.LENGTH_SHORT).show();//mcfee getaway or unprecedented network error
        }

    }


    }
    @Override
    public void onItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this,PropertyDetailsActivity.class);
        Listing clickedListing = propertyData.getListing().get(clickedItemIndex);
        intent.putExtra("LISTING_DETAILS",clickedListing);
        startActivity(intent);
    }
    ArrayList<String> ids = new ArrayList<String>();

    @Override
    public void onFavoriteClick(int likedItemIndex, ImageView favoriteButton) {
       addToFavorites(likedItemIndex,favoriteButton);


    }
public void addToFavorites(int likedItemIndex,ImageView favoriteButton){
    Listing likedListing =  propertyData.getListing().get(likedItemIndex);
    String likedListingId = likedListing.getListingId();
    Gson gson = new Gson();
    String listingGson = gson.toJson(likedListing);
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    if (!sharedPreferences.contains(likedListingId)){
        editor.putString(likedListingId,listingGson);
        editor.apply();//apply is better than commit because commit writes its data in persistent storage immediately while apply will handle it in the background
        favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
    }
    else{
        editor.remove(likedListingId);
        editor.apply();
        favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

}
}
