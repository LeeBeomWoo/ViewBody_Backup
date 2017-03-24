package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-08-04.
 */
public class BdItem {
    private String bd_Category;
    private Class BodyClass;

    public BdItem(String bd_Category, Class BodyClass){
        this.bd_Category = bd_Category;
        this.BodyClass = BodyClass;
    }

    public String getBd_Category(){
        return bd_Category;
    }

    public void setBd_Category(String bd_Category){
        this.bd_Category = bd_Category;
    }

    public Class getFragmentClass(){
        return BodyClass;
    }

    public void setFragmentClass(Class fragmentClass){
        this.BodyClass = fragmentClass;
    }
}

