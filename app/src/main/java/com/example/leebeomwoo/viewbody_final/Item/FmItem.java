package com.example.leebeomwoo.viewbody_final.Item;


/**
 * Created by LBW on 2016-05-24.
 */

    public class FmItem {

    private String fm_ImageUrl;
    private String fm_Content;
    private String fm_Id;
    private String fm_Title;
    private String fm_ConectCode;

    public FmItem(String fm_Id, String fm_Title, String fm_Content, String fm_ImageUrl, String fm_ConectCode){
        this.fm_Id = fm_Id;
        this.fm_Title = fm_Title;
        this.fm_Content = fm_Content;
        this.fm_ImageUrl = fm_ImageUrl;
        this.fm_ConectCode = fm_ConectCode;
    }

    public String getFm_ImageUrl(){
        return fm_ImageUrl;
    }

    public void setFm_ImageUrl(String fm_ImageUrl){
        this.fm_ImageUrl = fm_ImageUrl;
    }

    public String getFm_Id(){
        return fm_Id;
    }

    public void setFm_Id(String fm_Id){
        this.fm_Id = fm_Id;
    }

    public String getFm_Title(){
        return fm_Title;
    }

    public void setFm_Title(String fm_Title){
        this.fm_Title = fm_Title;
    }

    public String getFm_Content(){
        return fm_Content;
    }

    public void setFm_Content(String fm_Content){
        this.fm_Content = fm_Content;
    }

    public String getFm_ConectCode(){
        return fm_ConectCode;
    }

    public void setFm_ConectCode(String fm_ConectCode){
        this.fm_ConectCode = fm_ConectCode;
    }
}


