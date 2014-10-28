package com.web.map.app.model;

import java.util.Date;

public class TwittEntry {
    Date createdAt;
    String text;
    String userName;
    String location;
    double geoCoordinateX;
    double geoCoordinateY;

    public String toString() {
        return "{ createdAt: " + createdAt + "," + "userName: " + userName
                + ", " + "location:"+location+","+"geoCoordinate(X,Y): (" + geoCoordinateX + ","
                + geoCoordinateY + ")" + "text: " + text + "}";
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getGeoCoordinateX() {
        return geoCoordinateX;
    }

    public void setGeoCoordinateX(double geoCoordinateX) {
        this.geoCoordinateX = geoCoordinateX;
    }

    public double getGeoCoordinateY() {
        return geoCoordinateY;
    }

    public void setGeoCoordinateY(double geoCoordinateY) {
        this.geoCoordinateY = geoCoordinateY;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
