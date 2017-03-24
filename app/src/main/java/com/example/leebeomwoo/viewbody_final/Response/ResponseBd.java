package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.BdItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LBW on 2016-06-14.
 */
public class ResponseBd {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("bdItem")
    @Expose
    public List<BdItem> bdItem = new ArrayList<BdItem>();

    /**
     *
     * @return
     * The liItem
     */
    public List<BdItem> getBdItem() {
        return bdItem;
    }

    /**
     *
     * @param bdItem
     * The LowerItem
     */
    public void setBdItem(List<BdItem> bdItem) {
        this.bdItem = bdItem;
    }

    public ResponseBd withLiItem(List<BdItem> bdItem) {
        this.bdItem = bdItem;
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

    public ResponseBd withResult(String result) {
        this.result = result;
        return this;
    }

}
