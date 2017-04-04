package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.DetailItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeeBeomWoo on 2017-04-05.
 */

public class ResponseDetails {
        @SerializedName("result")
    @Expose
    private String result;
        @SerializedName("bdItem")
        @Expose
        public List<DetailItem> dItem = new ArrayList<DetailItem>();

        /**
         *
         * @return
         * The liItem
         */
        public List<DetailItem> getDtailItem() {
            return dItem;
        }

        /**
         *
         * @param dItem
         * The LowerItem
         */
        public void setBdItem(List<DetailItem> dItem) {
            this.dItem = dItem;
        }

        public ResponseDetails withLiItem(List<DetailItem> dItem) {
            this.dItem = dItem;
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

        public ResponseDetails withResult(String result) {
            this.result = result;
            return this;
        }
}
