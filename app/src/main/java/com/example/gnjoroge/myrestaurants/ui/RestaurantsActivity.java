package com.example.gnjoroge.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gnjoroge.myrestaurants.Constants;
import com.example.gnjoroge.myrestaurants.R;
import com.example.gnjoroge.myrestaurants.adapters.RestaurantListAdapter;
import com.example.gnjoroge.myrestaurants.services.YelpService;
import com.example.gnjoroge.myrestaurants.models.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Callback;
import okhttp3.Call;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {

    //private SharedPreferences mSharedPreferences;
    //private String mRecentAddress;

    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;

    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getRestaurants(location);

        //testing that shared preferences is saving information
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if(mRecentAddress != null) {
//         getRestaurants(mRecentAddress);
//        }
    }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRestaurants = yelpService.processResults(response);

                RestaurantsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mAdapter = new RestaurantListAdapter(getApplicationContext(), mRestaurants);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RestaurantsActivity.this);

                        mRecyclerView.setAdapter(mAdapter);

                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}//end
