package com.example.android.realestatemaster.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.utils.QuerryBuilder;

public class SearchFilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button searchButton, applyFilterButton;
    private EditText searchEt;
    private SearchView searchView;
    private Spinner minPriceSp, maxPriceSp, bedroomsSp, distanceSp, sortBySp, propertyTypeSp;
    private EditText keywordsEt;
    private CheckBox newHomesCheck, soldHomesCheck, chainFreeHomesCheck;
    private QuerryBuilder querryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_buy_tab);
        //searchView = findViewById(R.id.searchView_buy);
        querryString = new QuerryBuilder();
        initializeComponents();
        bindSpinnerData();
        Spinner[] spinners = {maxPriceSp, minPriceSp, bedroomsSp, distanceSp, sortBySp, propertyTypeSp};
        setUpSpinnerOnClickListener(spinners);


    }

    public void bindSpinnerData() {
        setUpSpinner(R.array.maxPrice, maxPriceSp);
        setUpSpinner(R.array.minPrice, minPriceSp);
        setUpSpinner(R.array.minBedrooms, bedroomsSp);
        setUpSpinner(R.array.distanceFromLocation, distanceSp);
        setUpSpinner(R.array.sortBy, sortBySp);
        setUpSpinner(R.array.propertyType, propertyTypeSp);
   }

    public void initializeComponents() {
        searchEt = findViewById(R.id.searchEt);
        keywordsEt = findViewById(R.id.keywords_buy);
        newHomesCheck = findViewById(R.id.checkBox_new_homes_buy);
        soldHomesCheck = findViewById(R.id.checkBox_sold_buy);
        chainFreeHomesCheck = findViewById(R.id.checkBox_chain_free_buy);
        searchButton = findViewById(R.id.search_button_buy);
        applyFilterButton = findViewById(R.id.apply_filter);
        minPriceSp = findViewById(R.id.price_min_drop_buy);
        maxPriceSp = findViewById(R.id.price_max_drop_buy);
        bedroomsSp = findViewById(R.id.bedroom_drop__buy);
        distanceSp = findViewById(R.id.distance_from_location_drop__buy);
        sortBySp = findViewById(R.id.sortby_drop__buy);
        propertyTypeSp = findViewById(R.id.proporty_type_drop__buy);
        keywordsEt = findViewById(R.id.keywords_buy);
        newHomesCheck = findViewById(R.id.checkBox_new_homes_buy);
    }

    public void setUpSpinner(int itemsResource, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(getApplicationContext(), itemsResource, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int clickedSpinner = adapterView.getId();
        String getSelectedSpinnerClickedItem = adapterView.getAdapter().getItem(i).toString().replaceAll("Within ", "").replaceAll(" miles", "").replaceAll(" mile", "").replaceAll("\\+", "").replaceAll(",", "");
        switch (clickedSpinner) {
            case R.id.price_min_drop_buy: {
                if (!getSelectedSpinnerClickedItem.equalsIgnoreCase("no min")) {
                    querryString.setMinPrice(getSelectedSpinnerClickedItem);
                }

                break;
            }
            case R.id.price_max_drop_buy: {
                if (!getSelectedSpinnerClickedItem.equalsIgnoreCase("no max")) {
                    querryString.setMaxPrice(getSelectedSpinnerClickedItem);
                }

                break;
            }
            case R.id.bedroom_drop__buy: {
                querryString.setMinBeds(getSelectedSpinnerClickedItem);
                break;
            }
            case R.id.distance_from_location_drop__buy: {
                if (!getSelectedSpinnerClickedItem.equalsIgnoreCase("this area only")) {
                    querryString.setRadius(getSelectedSpinnerClickedItem);
                }

                break;
            }
            case R.id.sortby_drop__buy: {
                querryString.setOrderBy(getSelectedSpinnerClickedItem.toLowerCase());//has to be lower case otherwise..error
                break;
            }
            case R.id.proporty_type_drop__buy: {
                if (!getSelectedSpinnerClickedItem.equalsIgnoreCase("show all")) {
                    querryString.setPropertyType(getSelectedSpinnerClickedItem.toLowerCase());
                }

                break;
            }


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        showToast("Nothing selected");
    }

    public void setUpSpinnerOnClickListener(Spinner[] spinner) {
        for (int i = 0; i < spinner.length; i++) {
            spinner[i].setOnItemSelectedListener(this);
        }

    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void search(View view) {
        String areaInputString = searchEt.getText().toString();
        String keywordsString = keywordsEt.getText().toString();
        querryString.setKeywords(keywordsString);
        querryString.setIncludeNewHomes(String.valueOf(newHomesCheck.isChecked()));
        querryString.setIncludeNewHomes(String.valueOf(soldHomesCheck.isChecked()));
        querryString.setIncludeChainFreeHomes(String.valueOf(chainFreeHomesCheck.isChecked()));

        if (!(areaInputString.equals(""))) {
            querryString.setArea(areaInputString);
            String requestString = querryString.getQuerryString();
            SharedPreferences.Editor sharedPreferences = getSharedPreferences("RECENT_SEARCHES",MODE_PRIVATE).edit();
            sharedPreferences.putString("s",requestString);
            sharedPreferences.apply();
            Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, requestString);
            startActivity(intent);
            Log.d("ququ", requestString);
        } else {
            showToast("Enter an area please");
        }
    }

}
