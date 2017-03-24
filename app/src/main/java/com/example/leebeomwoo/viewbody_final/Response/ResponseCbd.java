package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.BdItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16. 8. 25.
 */

public class ResponseCbd {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("cbdItem")
    @Expose
    public List<BdItem> cbdItem = new ArrayList<BdItem>();

    /**
     *2
     * @return
     * The liItem
     */
    public List<BdItem> getCbdItem() {
        return cbdItem;
    }

    /**
     *
     * @param cbdItem
     * The LowerItem
     */
    public void setCbdItem(List<BdItem> cbdItem) {
        this.cbdItem = cbdItem;
    }

    public ResponseCbd withLiItem(List<BdItem> cbdItem) {
        this.cbdItem = cbdItem;
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

    public ResponseCbd withResult(String result) {
        this.result = result;
        return this;
    }
}
