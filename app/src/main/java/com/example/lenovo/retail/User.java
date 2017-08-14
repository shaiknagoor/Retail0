package com.example.lenovo.retail;

import android.os.Parcel;
import android.os.Parcelable;
import android.print.PrintAttributes;

/**
 * Created by Stampit-PC1 on 8/9/2017.
 */

public class User implements Parcelable {


    private String UName;
    private  String UEmail;
    private  String  Password;
    private  String MobileNo;
    private  String Conformpassword;
    private int CreatedByUserId;
    private  String CreatedDate;
    private  int UpdatedByUserId;
    private  String UpdatedDate;
    private int IsActive;

    public User() {

    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public String getConformpassword() {
        return Conformpassword;
    }

    public void setConformpassword(String conformpassword) {
        Conformpassword = conformpassword;
    }


    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUEmail() {
        return UEmail;
    }

    public void setUEmail(String UEmail) {
        this.UEmail = UEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public int getCreatedByUserId() {
        return CreatedByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public int getUpdatedByUserId() {
        return UpdatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }




    protected User(Parcel in) {
        UName = in.readString();
        UEmail = in.readString();
        Password = in.readString();
        Conformpassword = in.readString();
        MobileNo = in.readString();
        CreatedByUserId = in.readInt();
        CreatedDate = in.readString();
        UpdatedByUserId = in.readInt();
        UpdatedDate = in.readString();
        IsActive = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UName);
        dest.writeString(UEmail);
        dest.writeString(Password);
        dest.writeString(Conformpassword);
        dest.writeString(MobileNo);
        dest.writeInt(CreatedByUserId);
        dest.writeString(CreatedDate);
        dest.writeInt(UpdatedByUserId);
        dest.writeString(UpdatedDate);
        dest.writeInt(IsActive);
    }
}
