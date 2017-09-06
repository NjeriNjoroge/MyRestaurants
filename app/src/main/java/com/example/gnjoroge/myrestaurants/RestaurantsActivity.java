package com.example.gnjoroge.myrestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantsActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;
    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    private String[] restaurants = new String[] {"Java", "ArtCaffe",
            "Cafe Deli", "Kaldis", "Cold Stone Creamerys", "Galitos",
            "Hamdi", "Al Yusra", "Lord Errol", "Brew Bistro",
            "Tratorria", "Spur Steak", "Tamambo Restaurant",
            "NyamaMama", "Subway"};

    private String[] cuisines = new String[] {"Breakfast", "Breakfast", "Cakes",
            "Cakes", "Ice Cream", "Fast Food", "Arabic Food", "Anjera",
            "Noodle Soups", "Beer", "BBQ", "Cuban", "Bar Food", "Red-Meat Dishes", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        //implementing an arrayAdapter
        //update implemented custom ArrayAdapter
        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);
        mListView.setAdapter(adapter);

        //adding toast
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_SHORT).show();
                Log.v(TAG, "In the onItemClickListener!");
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurats near:" + location);
        Log.d(TAG, "Inthe onCreate method!");
    }

}
