package com.example.lenovo.retail;
import android.support.v4.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Lenovo on 7/10/2017.
 */

public class VegetableFragment  extends Fragment {

    private static final String Log_TAG = VegetableFragment.class.getSimpleName();
    private ListView lvVegetableProduct;
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

    public VegetableFragment(){

        //Required defualt constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.vegetable_fragment,container,false);
//        getActivity().setTitle("Vegetables");
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
        actionBar.setTitle("Vegetables");


        lvVegetableProduct = (ListView) rootView.findViewById(R.id.listview_vegetable);
        mDBHelper = new DataAcceshandler(getActivity());
        loadDataBase();
        return rootView;
    }

    private void loadDataBase() {

       /* //Check exists database
        File database = getActivity().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(getActivity())) {
                Toast.makeText(getActivity(), "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }*/
        //Get product list in db when db exists
        mProductList = mDBHelper.getListProduct();
        //Init adapter
        adapter = new ListProductAdapter(getActivity(), mProductList);
        //Set adapter for listview
        lvVegetableProduct.setAdapter(adapter);




    }
/*
    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Vegitable","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/

    public void setUpdateUiListener(AdapterView.OnItemClickListener onItemClickListener) {

    }
}
