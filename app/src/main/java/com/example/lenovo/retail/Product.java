package com.example.lenovo.retail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lenovo on 7/10/2017.
 */

public class Product implements Parcelable {
    private int id;
    private String name;
    private int price;
    private String description;
    private byte[] productPhoto;
    private String PhotoLocation;
    private  String FileExtension;
    private int itemCount;


    public Product(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public Product() {

    }



    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getId() {
        return id;
    }

    public byte[] getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(byte[] productPhoto) {
        this.productPhoto = productPhoto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhotoLocation() {
        return PhotoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        PhotoLocation = photoLocation;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    protected Product(Parcel in){

        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        description = in.readString();
        productPhoto = in.createByteArray();
        PhotoLocation = in.readString();
        FileExtension = in.readString();
        itemCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeByteArray(productPhoto);
        dest.writeString(PhotoLocation);
        dest.writeString(FileExtension);
        dest.writeInt(itemCount);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}


