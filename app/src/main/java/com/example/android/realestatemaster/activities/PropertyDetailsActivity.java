package com.example.android.realestatemaster.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.realestatemaster.HelperClasses.PropertyDataBinder;
import com.example.android.realestatemaster.HelperClasses.RecyclerViewAdapterSmall;
import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.databinding.ActivityDetailBinding;
import com.example.android.realestatemaster.utils.CustomSwipeAdapter;
import com.example.android.realestatemaster.utils.GsonModel.ErrorJsonModel;
import com.example.android.realestatemaster.utils.GsonModel.Listing;
import com.example.android.realestatemaster.utils.GsonModel.ProportyJsonModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.text.DecimalFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PropertyDetailsActivity extends AppCompatActivity implements RecyclerViewAdapterSmall.OnItemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Toolbar toolbar;
    private RecyclerViewAdapterSmall recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ProportyJsonModel propertyData;
    private ErrorJsonModel errorData;
    private ProgressBar progressBar;
    private Intent callIntent;
    private ActivityDetailBinding activityDetailBinding;
    private String searchString = "http://api.zoopla.co.uk/api/v1/property_listings.json?country=&county=&street=&postcode=&area=oxford&maximum_price=&minimum_price=&minimum_beds=1&maximum_beds=&property_type=&new_homes=false&keywords=&listing_id=&radius=&order_by=price&listing_status=sale&page_size=30&page_number=&chain_free=true&include_sold=null&api_key=w88jr4f54ba5e4pd4xmfy8ud";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        recyclerView = findViewById(R.id.detail_recycler_view);
        progressBar = findViewById(R.id.progress_detail);
        progressBar.setVisibility(View.GONE);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

          bindViews();

        if (isWifiEnabled()) {
            new FetchJson().execute(searchString); ///change this to recommend properties for sale and rent
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public Listing getListingInfoFromIntent() {
       Intent intent = getIntent();
       Listing currentListing = (Listing) intent.getSerializableExtra("LISTING_DETAILS");

       return currentListing;
    }

    @Override
    public void onItemClick(int clickedItemIndex) {

         Intent intent = new Intent(this, PropertyDetailsActivity.class);
         intent.putExtra("LISTING_DETAILS", propertyData.getListing().get(clickedItemIndex));
         startActivity(intent);
         finish();
    }

    public void share(View view) {
        /*Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = detailUrl;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Property Worth watching");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));*/
    }

    public class FetchJson extends AsyncTask<String, Void, String> {
        String jsonResponse;

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
            progressBar.setVisibility(View.GONE);
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

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerViewAdapter = new RecyclerViewAdapterSmall(getApplicationContext(), propertyData);
            recyclerViewAdapter.setOnItemClickListener(PropertyDetailsActivity.this);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());


        }


    }

    public boolean isWifiEnabled() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        //   Snackbar snackbar =  Snackbar.make(findViewById(R.id.parent_layout_id)," No connection ", Snackbar.LENGTH_INDEFINITE);

        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            //snackbar.dismiss();
            return true;
        } else {
            //snackbar.show();
            return false;
        }

    }

    public void callAgent(View view) {
        callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","+44"+getListingInfoFromIntent().getAgentPhone(),null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

                Toast.makeText(getApplicationContext(),"Calling agent "+getListingInfoFromIntent().getAgentName(),Toast.LENGTH_SHORT).show();
                startActivity(callIntent);


        }                           // good exept nothing happening after permission is granted
        else{
            try {
                Toast.makeText(getApplicationContext(),"Calling agent "+getListingInfoFromIntent().getAgentName(),Toast.LENGTH_SHORT).show();
                startActivity(callIntent);
            } catch(SecurityException e) {
                e.printStackTrace();
            }

        }
    }
    public void emailAgent(View view){
       Toast.makeText(getApplicationContext(),"Email Not Available",Toast.LENGTH_SHORT).show();
    }
    public void smsAgent(View view){
       String message = getString(R.string.sms_request);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "+44"+getListingInfoFromIntent().getAgentPhone()));

        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
        // other 'case' lines to check for other
        // permissions this app might request
    }

    public void bindViews(){
        Listing listing;
        ViewPager viewPager = findViewById(R.id.view_pager); //TODO bind this!!
        CustomSwipeAdapter adapter;
        if (getListingInfoFromIntent() != null ) {

            listing = getListingInfoFromIntent();

            String price = "£ "+ new DecimalFormat("#,###,###").format(Double.parseDouble(listing.getPrice()));;
            int bedroomNumber = Integer.parseInt(listing.getNumBedrooms());
            String bedrooms = (bedroomNumber>1)?bedroomNumber + " Bedrooms":bedroomNumber+" Bedroom";
            int bathroomNumber = Integer.parseInt(listing.getNumBathrooms());
            String bathrooms = (bathroomNumber>1)?bathroomNumber + " Bathrooms":bathroomNumber+" Bathroom";
            String area = "200sqft";
            String address = listing.getDisplayableAddress();
            String description = listing.getDescription();
            String similarArea = listing.getCounty() ;       //TODO similar area ?? choose what is considered similar.
            String agentPhone = listing.getAgentPhone();
            String detailUrl = listing.getDetailsUrl();

            String image0 = listing.getImage8060Url();
            String image1 = listing.getImage5038Url();
            String image2 = listing.getImage8060Url();
            String image3 = listing.getImage150113Url();
            String image4 = listing.getImage354255Url();
            String image5 = listing.getImage645430Url();
            String[] imageUrls={image0, image1, image2, image3, image4, image5};

            adapter = new CustomSwipeAdapter(this, imageUrls);
            viewPager.setAdapter(adapter);

            PropertyDataBinder propertyDataBinder = new PropertyDataBinder(price, bedrooms, bathrooms, area, address, agentPhone, description);
            activityDetailBinding.setDetail(propertyDataBinder);

            ExpandableTextView expandableTextView;
            expandableTextView = findViewById(R.id.expand_text_view);
            expandableTextView.setText(description);// i feel like this is a redundant line of code. b/c textview already dynamically boundb but it doesnt seem to work with our seting text.. i wonder why?
            //expandableTextView.setText("2121");



        }
    }
    }
