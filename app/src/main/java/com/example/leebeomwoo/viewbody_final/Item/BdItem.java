package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-08-04.
 */
public class BdItem {
    private String bd_ImageUrl;
    private String bd_Content;
    private String bd_Id;
    private String bd_Title;
    private String bd_ConectCode;
    private String bd_Category;


    public BdItem(String bd_Id, String bd_Title, String bd_Content, String bd_ImageUrl, String bd_ConectCode, String bd_Category){
        this.bd_Id = bd_Id;
        this.bd_Title = bd_Title;
        this.bd_Content = bd_Content;
        this.bd_ImageUrl = bd_ImageUrl;
        this.bd_ConectCode = bd_ConectCode;
        this.bd_Category = bd_Category;

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

    public String getBd_Content(){
        return bd_Content;
    }

    public void setBd_Content(String bd_Content){
        this.bd_Content = bd_Content;
    }

    public String getBd_ConectCode(){
        return bd_ConectCode;
    }

    public void setBd_ConectCode(String bd_ConectCode){
        this.bd_ConectCode = bd_ConectCode;
    }

    public String getBd_Category(){
        return bd_Category;
    }

    public void setBd_Category(String bd_Category){
        this.bd_Category = bd_Category;
    }

}

