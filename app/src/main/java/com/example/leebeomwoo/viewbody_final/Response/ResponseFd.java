package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.FdItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LBW on 2016-06-14.
 */
public class ResponseFd {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("fdItem")
    @Expose
    private List<FdItem> fdItem = new ArrayList<FdItem>();

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

    public ResponseFd withResult(String result) {
        this.result = result;
        return this;
    }

    /**
     *
     * @return
     * The fdItem
     */
    public List<FdItem> getFdItem() {
        return fdItem;
    }

    /**
     *
     * @param fdItem
     * The FdItem
     */
    public void setFdItem(List<FdItem> fdItem) {
        this.fdItem = fdItem;
    }

    public ResponseFd withFdItem(List<FdItem> fdItem) {
        this.fdItem = fdItem;
        return this;
    }
}
