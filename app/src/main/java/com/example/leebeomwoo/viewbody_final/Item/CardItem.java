package com.example.leebeomwoo.viewbody_final.Item;

/**
 * Created by LeeBeomWoo on 2017-04-05.
 */

public class CardItem {
    private String card_ImageUrl;
    private String card_Content;
    private String card_Id;
    private String card_Title;
    private String card_Category;

    public CardItem(String card_Id, String card_Title, String card_Content, String card_ImageUrl, String card_Category){
        this.card_Id = card_Id;
        this.card_Title = card_Title;
        this.card_Content = card_Content;
        this.card_ImageUrl = card_ImageUrl;
        this.card_Category = card_Category;
    }

    public String getCard_Content() {
        return card_Content;
    }

    public void setCard_Content(String card_Content) {
        this.card_Content = card_Content;
    }

    public String getCard_ImageUrl() {
        return card_ImageUrl;
    }

    public void setCard_ImageUrl(String card_ImageUrl) {
        this.card_ImageUrl = card_ImageUrl;
    }

    public String getCard_Id() {
        return card_Id;
    }

    public void setCard_Id(String card_Id) {
        this.card_Id = card_Id;
    }

    public String getCard_Title() {
        return card_Title;
    }

    public void setCard_Title(String card_Title) {
        this.card_Title = card_Title;
    }

    public String getCard_Category() {
        return card_Category;
    }

    public void setCard_Category(String card_Category) {
        this.card_Category = card_Category;
    }
}
