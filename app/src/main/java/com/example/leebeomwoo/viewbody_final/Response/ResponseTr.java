package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.ItemGroup.Award;
import com.example.leebeomwoo.viewbody_final.ItemGroup.License;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeeBW on 2016-07-14.
 */
public class ResponseTr {
    private String nickName;
    private String call;
    private String email;
    private String photoUrl;
    private String association;
    @SerializedName("license")
    @Expose
    private List<License> licenses = new ArrayList<License>();

    @SerializedName("award")
    @Expose
    private List<Award> awards = new ArrayList<Award>();

    public String getNickName(){
        return nickName;
    }

    public void setLi_ImageUrl(String NickName){
        this.nickName = NickName;
    }

    public String getCall(){
        return call;
    }

    public void setCall(String Call){
        this.call = Call;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhotoUrl(){
        return photoUrl;
    }

    public void setPhotoUrl(String PhotoUrl){
        this.photoUrl = PhotoUrl;
    }

    public String getAssociation(){
        return association;
    }

    public void setAssociation(String association){
        this.association = association;
    }

    public List<License> getLicense() {
        return licenses;
    }

    public void setLicense(List<License> licenses) {
        this.licenses = licenses;
    }

    public ResponseTr withLicense(List<License> licenses) {
        this.licenses = licenses;
        return this;
    }

    public List<Award> getAward() {
        return awards;
    }


    public void setAward(List<Award> awards) {
        this.awards = awards;
    }

    public ResponseTr withAward(List<Award> awards) {
        this.awards = awards;
        return this;
    }
}
