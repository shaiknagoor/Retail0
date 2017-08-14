package com.example.lenovo.retail;

/**
 * Created by Lenovo on 7/11/2017.
 */

public class Catagory {
    private int id;
    private String name;

    public Catagory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
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


}
