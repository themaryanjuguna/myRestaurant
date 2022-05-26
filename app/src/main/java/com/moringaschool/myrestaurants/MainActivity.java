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
    public static final String TAG = MainActivity.class.getSimpleName();
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
                Log.d(TAG, location);
                //do something
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                startActivity(intent);
              /*  String location = mFindLocationEditText.getText().toString();
                Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();*/

            }
        });

    }


}