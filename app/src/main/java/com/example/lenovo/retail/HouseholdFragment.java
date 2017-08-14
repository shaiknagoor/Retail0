package com.example.lenovo.retail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Lenovo on 7/11/2017.
 */

public class HouseholdFragment extends Fragment {
    private static final String Log_TAG = HouseholdFragment.class.getSimpleName();
    private ListView lvHouseholdProduct;
    private UpdateUiListener updateUiListener;
    private ListProductAdapter adapter;
    private List<Product> mProductList;
    private DataAcceshandler mDBHelper = null;
    private View rootView;
    private Context mContext;
    private ActionBar actionBar;
    public void setUpdateUiListener(UpdateUiListener updateUiListener) {
        this.updateUiListener = updateUiListener;
    }

    public HouseholdFragment(){

        //Required defualt constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.household_fragment,container,false);
//        getActivity().setTitle("Household");
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mContext = getActivity();
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("HouseHold");
        lvHouseholdProduct = (ListView) rootView.findViewById(R.id.listview_household);
        mDBHelper = new DataAcceshandler(getActivity());
        loadDataBase();
        return rootView;
    }

    private void loadDataBase() {


        //Get product list in db when db exists
        mProductList = mDBHelper.getHouseholdProduct();
        //Init adapter
        adapter = new ListProductAdapter(getActivity(), mProductList);
        //Set adapter for listview
        lvHouseholdProduct.setAdapter(adapter);


    }



    public void setUpdateUiListener(AdapterView.OnItemClickListener onItemClickListener) {

    }
}
