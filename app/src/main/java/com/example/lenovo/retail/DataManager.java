package com.example.lenovo.retail;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stampit-PC1 on 8/9/2017.
 */

public class DataManager<T> {

    private static final String LOG_TAG = DataManager.class.getName();
    private static final DataManager instance = new DataManager();
    public static final String USER_DETAILS = "user_details";

    private final Map<String, T> dataMap = new ConcurrentHashMap<>();

    public static DataManager getInstance() {
        return instance;
    }




    public synchronized T getDataFromManager(final String type) {
        return dataMap.get(type);
    }

    public void deleteData(String type) {
        if (dataMap.get(type) != null) {
            dataMap.remove(type);
        }
    }

    public synchronized void addData(final String type, final T data) {
//        deleteData(type);
        dataMap.put(type, data);
    }
}
