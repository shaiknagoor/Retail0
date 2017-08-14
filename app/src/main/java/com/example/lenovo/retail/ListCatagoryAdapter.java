package com.example.lenovo.retail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 7/11/2017.
 */

public class ListCatagoryAdapter extends BaseAdapter{
    private Context mContext;

    public ListCatagoryAdapter(Context mContext, List<Catagory> mCatagoryList) {
        this.mContext = mContext;
        this.mCatagoryList = mCatagoryList;
    }

    private List<Catagory> mCatagoryList;


    @Override
    public int getCount() {
        return mCatagoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCatagoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCatagoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_listview_catagory, null);
        TextView tvName=(TextView)v.findViewById(R.id.tv_catagory_name);
        tvName.setText(mCatagoryList.get(position).getName());
        return v;
    }
}
