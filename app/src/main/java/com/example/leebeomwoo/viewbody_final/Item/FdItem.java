package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-05-24.
 */

/** public String getBd_Category(){
 return bd_Category;
 }
 //  private String bd_word;
 //  this.bd_word = bd_word;
 public void setBd_Category(String bd_Category){
 this.bd_Category = bd_Category;
 }
 */
public class FdItem {

    private String fd_ImageUrl;
    private String fd_Id;
    private String fd_Title;

public FdItem(String fd_Id, String fd_Title, String fd_ImageUrl){
    this.fd_Id = fd_Id;
    this.fd_Title = fd_Title;
    this.fd_ImageUrl = fd_ImageUrl;
}

    public String getFd_ImageUrl(){
        return fd_ImageUrl;
    }

    public void setFd_ImageUrl(String fd_ImageUrl){
        this.fd_ImageUrl = fd_ImageUrl;
    }

    public String getFd_Id(){
        return fd_Id;
    }

    public void setFd_Id(String fd_Id){
        this.fd_Id = fd_Id;
    }

    public String getFd_Title(){
        return fd_Title;
    }

    public void setFd_Title(String fd_Title){
        this.fd_Title = fd_Title;
    }
}
