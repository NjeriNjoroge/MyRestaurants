package com.example.gnjoroge.myrestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantsActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private ListView mListView;

    private String[] restaurants = new String[] {"Java", "ArtCaffe",
            "Cafe Deli", "Kaldis", "Cold Stone Creamerys", "Galitos",
            "Hamdi", "Al Yusra", "Lord Errol", "Brew Bistro",
            "Tratorria", "Spur Steak", "Tamambo Restaurant",
            "NyamaMama", "Subway"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        mListView = (ListView) findViewById(R.id.listView); //initializing the list view
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        //implementing an arrayAdapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
        mListView.setAdapter(adapter);

        //adding toast
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurats near:" + location);
    }

}
