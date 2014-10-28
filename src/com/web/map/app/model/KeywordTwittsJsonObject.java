package com.web.map.app.model;

import java.util.ArrayList;

public class KeywordTwittsJsonObject {
    String id;
    String keyword;
    ArrayList<TwittEntry> twitts;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public ArrayList<TwittEntry> getTwitts() {
        return twitts;
    }
    public void setTwitts(ArrayList<TwittEntry> twitts) {
        this.twitts = twitts;
    }
}
