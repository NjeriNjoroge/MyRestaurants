package com.example.gnjoroge.myrestaurants;

/**
 * Created by gnjoroge on 9/5/17.
 */

import android.os.Build;
import android.widget.ListView;

import com.example.gnjoroge.myrestaurants.ui.RestaurantsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class RestaurantsActivityTest {
    private RestaurantsActivity activity;
    private ListView mRestaurantListView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(RestaurantsActivity.class);
        mRestaurantListView = (ListView) activity.findViewById(R.id.listView);
    }

    @Test
    public void setmRestaurantListViewPopulates() {
        assertNotNull(mRestaurantListView.getAdapter());
        assertEquals(mRestaurantListView.getAdapter().getCount(), 15);
    }
}
