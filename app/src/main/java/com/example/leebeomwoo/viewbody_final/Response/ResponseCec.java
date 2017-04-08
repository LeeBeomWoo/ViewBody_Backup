package com.example.leebeomwoo.viewbody_final.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16. 8. 25.
 */

public class ResponseCec {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("cecItem")
    @Expose
    public List<EcItem> ecItem = new ArrayList<EcItem>();

    /**
     *
     * @return
     * The liItem
     */
    public List<EcItem> getEcItem() {
        return ecItem;
    }

    /**
     *
     * @param ecItem
     * The LowerItem
     */
    public void setEcItem(List<EcItem> ecItem) {
        this.ecItem = ecItem;
    }

    public ResponseCec withLiItem_ex(List<EcItem> ecItem) {
        this.ecItem = ecItem;
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

    public ResponseCec withResult(String result) {
        this.result = result;
        return this;
    }
}
