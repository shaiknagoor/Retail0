package com.example.lenovo.retail;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 7/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "sampledb.sqlite";
    private static  String DBLOCATION ;
    private Context mContext;
//    private SQLiteDatabase mDatabase;
    private static SQLiteDatabase mSqliteDatabase = null;
    private static  DatabaseHelper mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
        DBLOCATION = "/sdcard/RetailDatabase/";
        Log.v("The Database Path", DBLOCATION);
    }


    public static synchronized DatabaseHelper getRetailDatabase(Context context) {
       {
            if ( mDatabase == null) {
                mDatabase = new DatabaseHelper(context);
            }
            return mDatabase;
        }

    }

    public static SQLiteDatabase openDataBaseNew() throws SQLException
    {
        // Open the database
        if (mSqliteDatabase == null)
        {

            mSqliteDatabase = SQLiteDatabase.openDatabase(DBLOCATION, null,SQLiteDatabase.OPEN_READWRITE| SQLiteDatabase.CREATE_IF_NECESSARY| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }
        else if (!mSqliteDatabase.isOpen())
        {
            mSqliteDatabase = SQLiteDatabase.openDatabase(DBLOCATION, null,SQLiteDatabase.OPEN_READWRITE| SQLiteDatabase.CREATE_IF_NECESSARY| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }
        return mSqliteDatabase;
    }

    /* create an empty database if data base is not existed */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {
            try {
                copyDataBase();
                Log.v("dbcopied:::","true");
            } catch (SQLiteException ex) {
                ex.printStackTrace();
                throw new Error("Error copying database");
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Error copying database");
            }
            try {
                openDataBase();
            } catch (SQLiteException ex) {
                ex.printStackTrace();
                throw new Error("Error opening database");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Error("Error opening database");
            }
        }

    }

    /* checking the data base is existed or not */
    /* return true if existed else return false */
    private boolean checkDataBase() {
        boolean dataBaseExisted = false;
        try {
            String check_Path = DBLOCATION + DBNAME;
            File file = new File(check_Path);
            if (file.exists() && !file.isDirectory())
            mSqliteDatabase = SQLiteDatabase.openDatabase(check_Path, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
        return mSqliteDatabase != null ? true : false;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

    private void copyDataBase() throws IOException {
        File dbDir = new File(DBLOCATION);
        if (!dbDir.exists()) {
            dbDir.mkdir();

        }
        InputStream myInput = mContext.getAssets().open(DBNAME);
        OutputStream myOutput = new FileOutputStream(DBLOCATION + DBNAME);
        copyFile(myInput,myOutput);

    }

    /* Open the database */
    public void openDataBase() throws SQLException {

        String check_Path = DBLOCATION + DBNAME;
        if (mSqliteDatabase != null) {
            mSqliteDatabase.close();
            mSqliteDatabase = null;
            mSqliteDatabase = SQLiteDatabase.openDatabase(check_Path, null, SQLiteDatabase.OPEN_READWRITE);
        } else {
            mSqliteDatabase = SQLiteDatabase.openDatabase(check_Path, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getSinlgeEntry(String userName)
    {
        openDatabase();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=mSqliteDatabase.query("User", null, " UName=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }



    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mSqliteDatabase != null && mSqliteDatabase.isOpen()) {
            return;
        }
        mSqliteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mSqliteDatabase!=null) {
            mSqliteDatabase.close();
        }
    }

}
