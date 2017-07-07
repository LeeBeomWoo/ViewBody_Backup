package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LBW on 2016-08-04.
 */
public class ListDummyItem {
    private String ld_ImageUrl;
    private String ld_Id;
    private String ld_Title;
    private String ld_FaceUrl;
    private int ld_Section;
    private String ld_Video;
    private int ld_Pop;
    private int ld_Num;


    public ListDummyItem(String ld_Id, String ld_Title, String ld_ImageUrl, String ld_FaceUrl, int ld_Section, String ld_video, int ld_Num, int ld_Pop){
        this.ld_Id = ld_Id;
        this.ld_Title = ld_Title;
        this.ld_ImageUrl = ld_ImageUrl;
        this.ld_FaceUrl = ld_FaceUrl;
        this.ld_Section = ld_Section;
        this.ld_Video = ld_video;
        this.ld_Num = ld_Num;
        this.ld_Pop = ld_Pop;
    }

    public String getLd_Video() {
        return ld_Video;
    }

    public void setLd_Video(String ld_Video) {
        this.ld_Video = ld_Video;
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

    public String getLd_FaceUrl() {
        return ld_FaceUrl;
    }

    public void setLd_FaceUrl(String ld_FaceUrl) {
        this.ld_FaceUrl = ld_FaceUrl;
    }

    public int getLd_Section() {
        return ld_Section;
    }

    public void setLd_Section(int ld_Section) {
        this.ld_Section = ld_Section;
    }

    public int getLd_Num() {
        return ld_Num;
    }

    public void setLd_Num(int ld_Num) {
        this.ld_Num = ld_Num;
    }

    public int getLd_Pop() {
        return ld_Pop;
    }

    public void setLd_Pop(int ld_Pop) {
        this.ld_Pop = ld_Pop;
    }
}

