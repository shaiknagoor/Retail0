package com.example.lenovo.retail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.lenovo.retail.HomeScreen.LOG_TAG;

/**
 * Created by Stampit-PC1 on 7/14/2017.
 */

public class DataAcceshandler {

    private SQLiteDatabase mDatabase;

    public DataAcceshandler(final Context context) {
        try {
            mDatabase = DatabaseHelper.openDataBaseNew();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeRawQuery(String query) {
        try {
            if (mDatabase != null) {
                mDatabase.execSQL(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<Catagory> getListCatagory() {
        Catagory catagory = null;
        List<Catagory> productCatagoryList = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery("select * from Catagory", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            catagory = new Catagory(cursor.getInt(0), cursor.getString(1));
            productCatagoryList.add(catagory);
            Log.v("@@@data", String.valueOf(productCatagoryList));
            cursor.moveToNext();
        }
        cursor.close();
        return productCatagoryList;
    }

    public List<Product> getListProduct() {
        List<Product> productList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor =  mDatabase.rawQuery("select * from Product where CatagoryId = 1", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDescription(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setPhotoLocation(cursor.getString(4));
                    product.setFileExtension(cursor.getString(5));
                    productList.add(product);
                    Log.v("@@@data", String.valueOf(productList));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            return productList;
        }
    }

    public List<Product> getBakeryProduct() {

        List<Product> bakeryList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor =  mDatabase.rawQuery("select * from Product where CatagoryId = 2", null);

            if (cursor != null && cursor.moveToFirst()) {

                do {

                    Product product = new Product();

                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDescription(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setPhotoLocation(cursor.getString(4));
                    product.setFileExtension(cursor.getString(5));

                    bakeryList.add(product);
                    Log.v("@@@data", String.valueOf(bakeryList));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

            return bakeryList;
        }
    }


    public List<Product> getHouseholdProduct() {

        List<Product> householdList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor =  mDatabase.rawQuery("select * from Product where CatagoryId = 5", null);

            if (cursor != null && cursor.moveToFirst()) {

                do {

                    Product product = new Product();

                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDescription(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setPhotoLocation(cursor.getString(4));
                    product.setFileExtension(cursor.getString(5));

                    householdList.add(product);
                    Log.v("@@@data", String.valueOf(householdList));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

            return householdList;
        }
    }


    public List<Product> getPersonalProduct() {

        List<Product> personalList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor =  mDatabase.rawQuery("select * from Product where CatagoryId = 4", null);

            if (cursor != null && cursor.moveToFirst()) {

                do {

                    Product product = new Product();

                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDescription(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setPhotoLocation(cursor.getString(4));
                    product.setFileExtension(cursor.getString(5));

                    personalList.add(product);
                    Log.v("@@@data",String.valueOf(personalList));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

            return personalList;
        }
    }



    public List<Product> getBeveragesProduct() {

        List<Product> beveragesList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor =  mDatabase.rawQuery("select * from Product where CatagoryId = 3", null);

            if (cursor != null && cursor.moveToFirst()) {

                do {

                    Product product = new Product();

                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDescription(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setPhotoLocation(cursor.getString(4));
                    product.setFileExtension(cursor.getString(5));

                    beveragesList.add(product);
                    Log.v("@@@data", String.valueOf(beveragesList));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

            return beveragesList;
        }
    }

  /*  public void insertEntry(String username,int mobileno,String email,String Password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("UName", username);
        newValues.put("MobileNo",mobileno);
        newValues.put("UEmail",email);
        newValues.put("Password",Password);

        // Insert the row into your table
        mDatabase.insert("User", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }*/

  /*  public String getSinlgeEntry(String userName)
    {
        Cursor cursor=mDatabase.query("User", null, " UName=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }*/

    public synchronized void userinsertData(String tableName, List<LinkedHashMap> mapList, Context context, final ApplicationThread.OnComplete<String> oncomplete) {
        int checkCount = 0;
        try {
            List<ContentValues> values1 = new ArrayList<>();
            for (int i = 0; i < mapList.size(); i++) {
                checkCount++;
                List<LinkedHashMap.Entry> entryList = new ArrayList<>((mapList.get(i)).entrySet());

                ContentValues contentValues = new ContentValues();
                for (LinkedHashMap.Entry temp : entryList) {
                    contentValues.put(temp.getKey().toString(), temp.getValue().toString());
                }
                values1.add(contentValues);
            }
            Log.v(LOG_TAG, "@@@@ log check " + checkCount + " here " + values1.size());
            boolean hasError = bulkinserttoTable(values1, tableName);
            if (hasError) {
                Log.v(LOG_TAG, "@@@ Error while inserting data ");
                if (null != oncomplete) {
                    oncomplete.execute(false, "failed to insert data", "");
                }
            } else {
                Log.v(LOG_TAG, "@@@ data inserted successfully for table :" + tableName);
                if (null != oncomplete) {
                    oncomplete.execute(true, "data inserted successfully", "");
                }
            }
        } catch (Exception e) {
            checkCount++;
            e.printStackTrace();
            Log.v(LOG_TAG, "@@@@ exception log check " + checkCount + " here " + mapList.size());
            if (checkCount == mapList.size()) {
                if (null != oncomplete)
                    oncomplete.execute(false, "data insertion failed", "" + e.getMessage());
            }
        } finally {
            closeDataBase();
        }
    }


    public void closeDataBase() {
        if (mDatabase != null)
            mDatabase.close();
    }


    public boolean bulkinserttoTable(List<ContentValues> cv, final String tableName) {
        boolean isError = false;
        mDatabase.beginTransaction();
        try {
            for (int i = 0; i < cv.size(); i++) {
                ContentValues stockResponse = cv.get(i);
                long id = mDatabase.insert(tableName, null, stockResponse);
                if (id < 0) {
                    isError = true;
                }
            }
            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }
        return isError;
    }

 /*   public String getSinlgeEntry(String password)
    {
        //String password = null;
        Cursor cursor=mDatabase.query("User", null," Password=?", new String[]{password}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
//        String conformPass= cursor.getString(cursor.getColumnIndex("ConformPassword"));
        cursor.close();
        return password;
    }*/

}
