package com.moringaschool.myrestaurants.ui;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.moringaschool.myrestaurants.MyRestaurantsArrayAdapter;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.models.Category;
import com.moringaschool.myrestaurants.models.YelpBusinessesSearchResponse;
import com.moringaschool.myrestaurants.network.YelpApi;
import com.moringaschool.myrestaurants.network.YelpClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantActivity extends AppCompatActivity {
    private static final String TAG = RestaurantActivity.class.getSimpleName();

    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.bind(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Business> restaurantsList = response.body().getBusinesses();
                    String[] restaurants = new String[restaurantsList.size()];
                    String[] categories = new String[restaurantsList.size()];

                    for (int i = 0; i < restaurants.length; i++){
                        restaurants[i] = restaurantsList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = restaurantsList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter
                            = new MyRestaurantsArrayAdapter(RestaurantActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
                    mListView.setAdapter(adapter);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mListView.setVisibility(View.VISIBLE);
        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}


/*
public class RestaurantActivity extends AppCompatActivity {
    private TextView mFindLocationTextView;
    private static final String TAG = RestaurantsActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

   */
/* private String[] restaurants = new String[] {"Mi Mero Mole", "Mother's Bistro", "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina", "Lardo", "Portland City Grill", "Fat Head's Brewery", "Chipotle", "Subway"};
    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };*//*



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //making the request
        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");

        //Processing the response
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {

                if (response.isSuccessful()) {
                    List<Business> restaurantsList = response.body().getBusinesses();
                    String[] restaurants = new String[restaurantsList.size()];
                    String[] categories = new String[restaurantsList.size()];
                    for (int i=0; i < restaurants.length; i++){
                        restaurants[i] = restaurantsList.get(i).getname();
                    }
                    for (int i = 0; i < categories.length; i++) {
                        Category category = restaurantsList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }
                    ArrayAdapter adapter
                            = new MyRestaurantArrayAdapter(RestaurantActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
                    mListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mFindLocationTextView = (TextView) findViewById(R.id.findLocationTextView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mFindLocationTextView.setText("Here are all the restaurants near: " + location);
    }
}*/
