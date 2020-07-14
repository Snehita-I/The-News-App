package com.example.json_volley;

public class ModelClass {

    String title1, url;
    public ModelClass(){}

    public ModelClass(String title, String description) {
        title1 = title;
        url = description;

    }

    public String getString(){ return title1;}
    public String getUrl()  {return url;}
}