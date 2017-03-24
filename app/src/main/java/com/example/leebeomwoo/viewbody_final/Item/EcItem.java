package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-08-04.
 */
public class EcItem {
    private String ec_ImageUrl;
    private String ec_Content;
    private String ec_Id;
    private String ec_Title;
    private String ec_ConectCode;
    private String ec_Category;

    public EcItem(String ec_Id, String ec_Title, String ec_Content, String ec_ImageUrl, String ec_ConectCode, String ec_Category){
        this.ec_Id = ec_Id;
        this.ec_Title = ec_Title;
        this.ec_Content = ec_Content;
        this.ec_ImageUrl = ec_ImageUrl;
        this.ec_ConectCode = ec_ConectCode;
        this.ec_Category = ec_Category;
    }

    public String getEc_ImageUrl(){
        return ec_ImageUrl;
    }

    public void setEc_ImageUrl(String ec_ImageUrl){
        this.ec_ImageUrl = ec_ImageUrl;
    }

    public String getEc_Id(){
        return ec_Id;
    }

    public void setEc_Id(String ec_Id){
        this.ec_Id = ec_Id;
    }

    public String getEc_Title(){
        return ec_Title;
    }

    public void setEc_Title(String ec_Title){
        this.ec_Title = ec_Title;
    }

    public String getEc_Content(){
        return ec_Content;
    }

    public void setEc_Content(String ec_Content){
        this.ec_Content = ec_Content;
    }

    public String getEc_ConectCode(){
        return ec_ConectCode;
    }

    public void setEc_ConectCode(String ec_ConectCode){
        this.ec_ConectCode = ec_ConectCode;
    }

    public String getEc_Category(){
        return ec_Category;
    }

    public void setEc_Category(String ec_Category){
        this.ec_Category = ec_Category;
    }
}
