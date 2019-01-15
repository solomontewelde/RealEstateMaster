package com.example.android.realestatemaster.Fragments;

import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
        import android.widget.SearchView;
        import android.widget.Spinner;
        import android.widget.Toast;
        import com.example.android.realestatemaster.R;
        import com.example.android.realestatemaster.activities.ResultsActivity;
        import com.example.android.realestatemaster.utils.QuerryBuilder;


public class RentTabFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Button searchButton, applyFilterButton;
    private EditText searchEt;
    private SearchView searchView;
    private Spinner minPriceSp, maxPriceSp, bedroomsSp, distanceSp, sortBySp, propertyTypeSp;
    private EditText keywordsEt;
    private QuerryBuilder querryString;
    private CheckBox sharedAccomodation, letAgreed;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_rent_tab, container, false);

        querryString = new QuerryBuilder();
        initializeComponents(view);
        bindSpinnerData();

        Spinner[] spinners = {maxPriceSp, minPriceSp, bedroomsSp, distanceSp, sortBySp, propertyTypeSp};
        setUpSpinnerOnClickListener(spinners);
        search();
        return view;
    }
    public void bindSpinnerData() {
        setUpSpinner(R.array.maxPrice, maxPriceSp);
        setUpSpinner(R.array.minPrice, minPriceSp);
        setUpSpinner(R.array.minBedrooms, bedroomsSp);
        setUpSpinner(R.array.distanceFromLocation, distanceSp);
        setUpSpinner(R.array.sortBy, sortBySp);
        setUpSpinner(R.array.propertyType, propertyTypeSp);
 }

    public void initializeComponents(View view) {
        searchEt = view.findViewById(R.id.searchEt);
        keywordsEt = view.findViewById(R.id.keywords_buy);
        searchButton = view.findViewById(R.id.search_button_buy);
        applyFilterButton = view.findViewById(R.id.apply_filter);
        minPriceSp = view.findViewById(R.id.price_min_drop_buy);
        maxPriceSp = view.findViewById(R.id.price_max_drop_buy);
        bedroomsSp = view.findViewById(R.id.bedroom_drop__buy);
        distanceSp = view.findViewById(R.id.distance_from_location_drop__buy);
        sortBySp = view.findViewById(R.id.sortby_drop__buy);
        propertyTypeSp = view.findViewById(R.id.proporty_type_drop__buy);
        keywordsEt = view.findViewById(R.id.keywords_buy);
        sharedAccomodation = view.findViewById(R.id.checkBox_shared_accomodation_rent);
        letAgreed = view.findViewById(R.id.checkBox_let_or_let_agreed_rent);
   }

    public void setUpSpinner(int itemsResource, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), itemsResource, android.R.layout.simple_spinner_dropdown_item);
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




    public void search() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String areaInputString = searchEt.getText().toString();


                if (!(areaInputString.equals(""))) {
                    querryString.setArea(areaInputString);
                    querryString.setListingStatus("rent");
                    querryString.setSharedAccomodation(String.valueOf(sharedAccomodation.isChecked()));
                    querryString.setLetAgreed(String.valueOf(sharedAccomodation.isChecked()));
                    querryString.setKeywords( keywordsEt.getText().toString());String requestString = querryString.getQuerryString();
                    Intent intent = new Intent(getActivity().getApplicationContext(), ResultsActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, requestString);
                    startActivity(intent);
                    Log.d("ququ", requestString);
                } else {
                    showToast("Enter an area please");
                }


            }
        });


    }
    public void showToast(String message) {

        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}