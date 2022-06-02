package com.moringaschool.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RestaurantActivity extends AppCompatActivity {
    private TextView mFindLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mFindLocationTextView = (TextView) findViewById(R.id.findLocationTextView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mFindLocationTextView.setText("Here are all the restaurants near: " + location);
    }
}