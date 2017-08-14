package com.example.lenovo.retail;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Stampit-PC1 on 7/15/2017.
 */

public class CommonUtills {
    public static final int REQUEST_CAM_PERMISSIONS = 1;
    public static final int FROM_CAMERA = 1;
    public static final int FROM_GALLERY = 2;

    public static final int PERMISSION_CODE = 100;


    public static boolean areAllPermissionsAllowed(final Context context, final String[] permissions) {
        boolean isAllPermissionsGranted = false;
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(context, permission);
            if (result == PackageManager.PERMISSION_GRANTED) {
                isAllPermissionsGranted = true;
            }
        }
        return isAllPermissionsGranted;
    }


    public static String getImageUrl(final Product product) {
        String url =  product.getPhotoLocation() + "/" + product.getFileExtension();
        return url.replace('\\', '/');
    }


    public static boolean isNewRegistration() {
        return (Constants.REGISTRATION_SCREEN_FROM.equalsIgnoreCase(Constants.REGISTRATION_SCREEN_FROM_NEW_USER));
    }

    public static String getcurrentDateTime(final String format) {
        Calendar c = Calendar.getInstance();
        String date;
        SimpleDateFormat Objdateformat = new SimpleDateFormat(format);
        date = Objdateformat.format(c.getTime());
        return date;
    }

    public static LinkedHashMap<String, Object> toMap(JSONObject object) throws JSONException {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }
    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
