package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.EcItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LBW on 2016-06-14.
 */
public class ResponseEc {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("ecItem")
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

    public ResponseEc withLiItem_ex(List<EcItem> ecItem) {
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

    public ResponseEc withResult(String result) {
        this.result = result;
        return this;
    }

}
