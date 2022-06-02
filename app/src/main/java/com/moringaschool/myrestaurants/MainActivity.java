package com.moringaschool.myrestaurants;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindRestaurantsButton;
    private EditText mFindLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindLocationEditText = (EditText) findViewById(R.id.findLocationEditText);
        mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);
        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String location = mFindLocationEditText.getText().toString();
               Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

    }


}