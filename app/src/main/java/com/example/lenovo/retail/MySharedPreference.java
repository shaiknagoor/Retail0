package com.example.lenovo.retail;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class MySharedPreference {
    private static final String Log_TAG = MySharedPreference.class.getSimpleName();
    private SharedPreferences prefs;

    private Context context;

    public MySharedPreference(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }



   /* public void addProductToTheCart(String product){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(Constants.PRODUCT_ID, product);
        edits.apply();
        edits.clear();
    }

    public String retrieveProductFromCart(){
        return prefs.getString(Constants.PRODUCT_ID, "");
    }*/

    public void addProductCount(int productCount){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(Constants.PRODUCT_COUNT, productCount);
        edits.apply();
        Log.v(Log_TAG,"@@@@@@@@@@@"+productCount);
         }


    public int retrieveProductCount()
    {

       return prefs.getInt(Constants.PRODUCT_COUNT, 0);
    }
}
