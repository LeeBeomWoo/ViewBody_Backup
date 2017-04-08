package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-08-04.
 */
public class BdItem {
    private String bd_ImageUrl;
    private String bd_Id;
    private String bd_Title;


    public BdItem(String bd_Id, String bd_Title, String bd_ImageUrl){
        this.bd_Id = bd_Id;
        this.bd_Title = bd_Title;
        this.bd_ImageUrl = bd_ImageUrl;

    }

    public String getBd_ImageUrl(){
        return bd_ImageUrl;
    }

    public void setBd_ImageUrl(String bd_ImageUrl){
        this.bd_ImageUrl = bd_ImageUrl;
    }

    public String getBd_Id(){
        return bd_Id;
    }

    public void setBd_Id(String bd_Id){
        this.bd_Id = bd_Id;
    }

    public String getBd_Title(){
        return bd_Title;
    }

    public void setBd_Title(String bd_Title){
        this.bd_Title = bd_Title;
    }

}

