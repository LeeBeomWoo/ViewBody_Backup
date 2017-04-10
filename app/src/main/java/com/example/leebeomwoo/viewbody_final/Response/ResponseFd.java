package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.ListDummyItem;
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
    private List<ListDummyItem> fdItem = new ArrayList<ListDummyItem>();

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
    public List<ListDummyItem> getFdItem() {
        return fdItem;
    }

    /**
     *
     * @param fdItem
     * The FdItem
     */
    public void setFdItem(List<ListDummyItem> fdItem) {
        this.fdItem = fdItem;
    }

    public ResponseFd withFdItem(List<ListDummyItem> fdItem) {
        this.fdItem = fdItem;
        return this;
    }
}
