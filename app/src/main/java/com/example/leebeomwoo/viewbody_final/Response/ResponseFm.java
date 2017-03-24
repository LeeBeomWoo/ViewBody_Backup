package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.FmItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LBW on 2016-06-14.
 */
public class ResponseFm {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("fmItem")
    @Expose
    private List<FmItem> fmItem = new ArrayList<FmItem>();

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

    public ResponseFm withResult(String result) {
        this.result = result;
        return this;
    }

    /**
     *
     * @return
     * The fmItem
     */
    public List<FmItem> getFmItem() {
        return fmItem;
    }

    /**
     *
     * @param fmItem
     * The FmItem
     */
    public void setFmItem(List<FmItem> fmItem) {
        this.fmItem = fmItem;
    }

    public ResponseFm withFmItem(List<FmItem> fmItem) {
        this.fmItem = fmItem;
        return this;
    }

}
