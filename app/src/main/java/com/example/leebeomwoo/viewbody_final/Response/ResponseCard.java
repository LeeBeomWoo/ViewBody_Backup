package com.example.leebeomwoo.viewbody_final.Response;

import com.example.leebeomwoo.viewbody_final.Item.BdItem;
import com.example.leebeomwoo.viewbody_final.Item.CardItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeeBeomWoo on 2017-04-05.
 */

public class ResponseCard {

    /**
     * Created by LBW on 2016-06-14.
     */
        @SerializedName("result")
        @Expose
        private String result;
        @SerializedName("bdItem")
        @Expose
        public List<CardItem> cardItem = new ArrayList<>();

        /**
         *
         * @return
         * The liItem
         */
        public List<CardItem> getCardItem() {
            return cardItem;
        }

        /**
         *
         * @param cardItem
         * The LowerItem
         */
        public void setCardItem(List<CardItem> cardItem) {
            this.cardItem = cardItem;
        }

        public ResponseCard withLiItem(List<CardItem> cardItem) {
            this.cardItem = cardItem;
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
