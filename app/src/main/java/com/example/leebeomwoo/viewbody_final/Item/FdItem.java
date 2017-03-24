package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-05-24.
 */
public class FdItem {

    private String fd_ImageUrl;
    private String fd_Content;
    private String fd_Id;
    private String fd_Title;
    private String fd_ConectCode;

public FdItem(String fd_Id, String fd_Title, String fd_Content, String fd_ImageUrl, String fd_ConectCode){
    this.fd_Id = fd_Id;
    this.fd_Title = fd_Title;
    this.fd_Content = fd_Content;
    this.fd_ImageUrl = fd_ImageUrl;
    this.fd_ConectCode = fd_ConectCode;
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

    public String getFd_Content(){
        return fd_Content;
    }

    public void setFd_Content(String fd_Content){
        this.fd_Content = fd_Content;
    }

    public String getFd_ConectCode(){
        return fd_ConectCode;
    }

    public void setFd_ConectCode(String fd_ConectCode){
        this.fd_ConectCode = fd_ConectCode;
    }
}
