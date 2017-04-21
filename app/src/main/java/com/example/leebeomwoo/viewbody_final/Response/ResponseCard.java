package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.CardItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LBW on 2016-06-14.
 */
public class ResponseCard {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("cdItem")
    @Expose
    public List<CardItem> cdItem = new ArrayList<>();
    /**
     *
     * @return
     * The liItem
     */
    public List<CardItem> getbCardItem() {
        return cdItem;
    }

    /**
     *
     * @param cdbItem
     * The LowerItem
     */
    public void setbCardItem(List<CardItem> cdbItem) {
        this.cdItem = cdItem;
    }

    public ResponseCard withbLiItem(List<CardItem> cdItem) {
        this.cdItem = cdItem;
        return this;
    }
    /**
     *
     * @return
     * The result
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(String result) {
        this.result = result;
    }

    public ResponseCard withResult(String result) {
        this.result = result;
        return this;
    }

}
