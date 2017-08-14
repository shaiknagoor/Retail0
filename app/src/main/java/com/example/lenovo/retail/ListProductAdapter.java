package com.example.lenovo.retail;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lenovo on 7/10/2017.
 */

public class ListProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mProductList;
    private Product mproduct;
    private String itemcount_str;


    private MySharedPreference sharedPreference;

//    private Product product;

    private final String imageInSD = "/sdcard/RetailDatabase/Amul.jpg";
public int i;

    public ListProductAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;

    }

    @Override
    public int getCount() {
//        notifyDataSetChanged();
        return mProductList.size();

    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_product_name);
        TextView tvPrice = (TextView)v.findViewById(R.id.tv_product_price);
        TextView tvDescription = (TextView)v.findViewById(R.id.tv_product_description);
        TextView add_item = (TextView) v.findViewById(R.id.add_item);
        TextView remove_item = (TextView) v.findViewById(R.id.remove_item);
        final TextView item_count = (TextView) v.findViewById(R.id.iteam_amount);
        final ImageView productImage = (ImageView) v.findViewById(R.id.product_image);
        tvName.setText(mProductList.get(position).getName());
        tvDescription.setText(mProductList.get(position).getDescription());

        sharedPreference = new MySharedPreference(v.getContext());
//
//        final GsonBuilder gsonBuilder = new GsonBuilder();
//        final Gson gson = gsonBuilder.create();

        final int[] cartProductNumber = {mProductList.get(position).getItemCount()};
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if (cartProductNumber[0]<25)
                  cartProductNumber[0]++;
//                notifyDataSetChanged();

//                itemcount_str = item_count.getText().toString();
//                mproduct.setItemCount(cartProductNumber[0]);
                sharedPreference.addProductCount(cartProductNumber[0]);
                Toast.makeText(v.getContext(), "cart count++ value"+cartProductNumber[0], Toast.LENGTH_SHORT).show();
                item_count.setText(String.valueOf(cartProductNumber[0]));
            }
        });
       remove_item.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (cartProductNumber[0]>0)
                   cartProductNumber[0]--;
               Toast.makeText(v.getContext(), "cart count-- value"+cartProductNumber[0], Toast.LENGTH_SHORT).show();
//               itemcount_str = item_count.getText().toString();
//               mproduct.setItemCount(cartProductNumber[0]);

//                cartProductNumber = (quantity[0]);
               sharedPreference.addProductCount(cartProductNumber[0]);
               item_count.setText(String.valueOf(cartProductNumber[0]));
           }


       });



        for (i = 0;i<imageInSD.length();i++){
//            Toast.makeText(this,"Total Images+"imageInSD.length(),Toast.LENGTH_LONG).show();
            Bitmap bitmap = BitmapFactory.decodeFile(imageInSD);
            productImage.setImageBitmap(bitmap);
        }



        tvPrice.setText("Rs:"+String.valueOf(mProductList.get(position).getPrice()) );
        final String imageUrl = CommonUtills.getImageUrl(mProductList.get(position));
//        final String imageUrl = "https://api.learn2crack.com/android/images/donut.png",
//                                  "https://api.learn2crack.com/android/images/kitkat.png",
//                                   "https://api.learn2crack.com/android/images/lollipop.png";


//        Picasso.with(mContext)
//                .load(imageUrl)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(productImage, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//                        //Try again online if cache failed
//                        Picasso.with(mContext)
//                                .load(imageUrl)
//                                .error(R.mipmap.ic_launcher)
//                                .into(productImage, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//
//                                    }
//
//                                    @Override

//                                    public void onError() {
//                                        Log.v("Picasso", "Could not fetch image");
//                                        if (!TextUtils.isEmpty(mProductList.get(position).getPhotoLocation())) {
//                                            loadImageFromStorage(mProductList.get(position).getPhotoLocation(), productImage);
//                                        }
//                                    }
//                                });
//                    }
//                });


        return v;
    }



/*
    private List<Product> convertObjectArrayToListObject(Product[] allProducts){
        List<Product> mProduct = new ArrayList<Product>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }*/

    private void loadImageFromStorage(String path, ImageView productImage) {

        try {
            File f = new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            if (null != b) {
                productImage.setImageBitmap(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}