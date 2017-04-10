package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-08-04.
 */
public class ListDummyItem {
    private String ld_ImageUrl;
    private String ld_Id;
    private String ld_Title;


    public ListDummyItem(String ld_Id, String ld_Title, String ld_ImageUrl){
        this.ld_Id = ld_Id;
        this.ld_Title = ld_Title;
        this.ld_ImageUrl = ld_ImageUrl;

    }

    public String getLd_ImageUrl(){
        return ld_ImageUrl;
    }

    public void setLd_ImageUrl(String ld_ImageUrl){
        this.ld_ImageUrl = ld_ImageUrl;
    }

    public String getLd_Id(){
        return ld_Id;
    }

    public void setLd_Id(String ld_Id){
        this.ld_Id = ld_Id;
    }

    public String getLd_Title() {
        return ld_Title;
    }

    public void setLd_Title(String ld_Title) {
        this.ld_Title = ld_Title;
    }
}

