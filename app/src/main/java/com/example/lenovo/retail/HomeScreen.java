package com.example.lenovo.retail;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class HomeScreen extends RetailBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String LOG_TAG = HomeScreen.class.getName();
    private VegetableFragment vegetableFragment;
    private BakeryFragment bakeryFragment;
    private BaveragesFragment baveragesFragment;
    private PersonalCareFragment personalCareFragment;
    private HouseholdFragment householdFragment;
    private ListView listItemView;
    private ListCatagoryAdapter adapter;
    private List<Catagory> mCatagoryList;
    private DataAcceshandler mDBHelper = null ;
    private DatabaseHelper databaseHelper;

    private Product mProduct;

    private SharedPreferences prefs;

    private Context context;

    private MySharedPreference sharedPreference;



 /*   private String[] PERMISSIONS_REQUIRED = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        sharedPreference = new MySharedPreference(HomeScreen.this);
        mDBHelper = new DataAcceshandler(this);
        listItemView = (ListView) findViewById(R.id.listview_catagory);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadDataBase();

    }

    @Override
    public void Initialize() {

    }




    private void loadDataBase() {


        //Get product list in db when db exists
        mCatagoryList = mDBHelper.getListCatagory();
        //Init adapter
        adapter = new ListCatagoryAdapter(this, mCatagoryList);
        //Set adapter for listview
        listItemView.setAdapter(adapter);



        // ListView setOnItemClickListener function apply here.

        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
//


                if (position == 0) {
                    vegetableFragment= new VegetableFragment();
                    vegetableFragment.setUpdateUiListener(this);
                    replaceFragment(vegetableFragment);
                } else if (position == 1) {
                    bakeryFragment= new BakeryFragment();
                    bakeryFragment.setUpdateUiListener(this);
                    replaceFragment(bakeryFragment);
                }
                else if (position == 2) {
                    baveragesFragment= new BaveragesFragment();
                    baveragesFragment.setUpdateUiListener(this);
                    replaceFragment(baveragesFragment);
                }
                else if (position == 3) {
                    personalCareFragment= new PersonalCareFragment();
                    personalCareFragment.setUpdateUiListener(this);
                    replaceFragment(personalCareFragment);
                }
                else if (position == 4) {
                    householdFragment= new HouseholdFragment();
                    householdFragment.setUpdateUiListener(this);
                    replaceFragment(householdFragment);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        MenuItem menuItem = menu.findItem(R.id.action_shop);
//
//        prefs = getApplicationContext().getSharedPreferences("MySharedPreference",
//                context.MODE_PRIVATE);
//        prefs.getInt(Constants.PRODUCT_COUNT,1);

        try {

    int mCount = sharedPreference.retrieveProductCount();

//        int mCount = mProduct.getItemCount();
    Toast.makeText(getApplicationContext(),"mcount"+mCount,Toast.LENGTH_LONG).show();
    Log.v(LOG_TAG,"mCount"+mCount);
    menuItem.setIcon(buildCounterDrawable(mCount,R.drawable.ic_shopping_cart_black_24dp));

}catch (NullPointerException e){
    e.printStackTrace();
            Log.v(LOG_TAG,"---------------"+e);
}

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            int mCount = sharedPreference.retrieveProductCount();

//        int mCount = mProduct.getItemCount();
            Toast.makeText(getApplicationContext(),"mcount_refresh"+mCount,Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.shopping_layout, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Intent intent=new Intent(getApplicationContext(),HomeScreen.class);
            startActivity(intent);
        } else if (id == R.id.vegetable) {
            VegetableFragment vegetableFragment = new VegetableFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,vegetableFragment)
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.bakery) {
            BakeryFragment bakeryFragment = new BakeryFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,bakeryFragment)
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.beverages) {
       BaveragesFragment baveragesFragment = new BaveragesFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,baveragesFragment)
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.personalCare) {
            PersonalCareFragment personalCareFragment = new PersonalCareFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,personalCareFragment)
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.household) {
            HouseholdFragment householdFragment = new HouseholdFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,householdFragment)
                    .addToBackStack(null)
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
