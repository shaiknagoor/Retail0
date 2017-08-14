package com.example.lenovo.retail;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stampit-PC1 on 8/9/2017.
 */

public class DataSavingHelper {

    private static final String LOG_TAG = DataSavingHelper.class.getName();
    public static void saveUserDetails(final Context context, final  ApplicationThread.OnComplete<String> onComplete) {

        User registrationDetails = (User) DataManager.getInstance().getDataFromManager(DataManager.USER_DETAILS);
        if (null == registrationDetails) {
            onComplete.execute(false, "data saving failed for User Details", "");
            return;
        }
        registrationDetails.setCreatedByUserId(Integer.parseInt(Constants.USER_ID));
        registrationDetails.setCreatedDate(CommonUtills.getcurrentDateTime(Constants.DATE_FORMAT_DDMMYYYY_HHMMSS));
        registrationDetails.setUpdatedByUserId(Integer.parseInt(Constants.USER_ID));
        registrationDetails.setUpdatedDate(CommonUtills.getcurrentDateTime(Constants.DATE_FORMAT_DDMMYYYY_HHMMSS));

        if (null != registrationDetails){
            Gson gson = new GsonBuilder().serializeNulls().create();
            JSONObject userData = null;
            List dataToInsert = null;
            try {
                userData = new JSONObject(gson.toJson(registrationDetails));
                dataToInsert = new ArrayList();
                dataToInsert.add(CommonUtills.toMap(userData));
            } catch (JSONException e) {
                Log.e(LOG_TAG, "@@@ error while converting user Details data");
            }
            Log.v(LOG_TAG, "@@ entered data " + userData.toString());

            final DataAcceshandler dataAccessHandler = new DataAcceshandler(context);


                dataAccessHandler.userinsertData(DataBaseKeys.TABLE_User, dataToInsert, context, new ApplicationThread.OnComplete<String>() {
                    @Override
                    public void execute(boolean success, String result, String msg) {
                        if (success) {
                            Log.v(LOG_TAG, "@@@ userDetails data saved successfully");
                        DataManager.getInstance().deleteData(DataManager.USER_DETAILS);
//                        if (CommonUtils.isNewRegistration()) {
//                            saveFarmerPictureData(context, oncomplete);
//                        } else {
//                            savePlotAddress(context, oncomplete);
//                        }
                        } else {
                            Log.e(LOG_TAG, "@@@ User Details data saving failed due to " + msg);
                        }
                    }
                });

        }


    }
}
