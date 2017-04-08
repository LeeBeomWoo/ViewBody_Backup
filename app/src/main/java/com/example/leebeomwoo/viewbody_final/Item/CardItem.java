package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LeeBeomWoo on 2017-03-27.
 */

    public class CardItem {
        private String ImageUrl;
        private String sEction;
        private String Id;
        private String Category;

        public CardItem(String Id, String ImageUrl, String Category){
            this.Id = Id;
            this.ImageUrl = ImageUrl;
            this.Category = Category;
        }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getsEction() {
        return sEction;
    }

    public void setsEction(String sEction) {
        this.sEction = sEction;
    }
}
