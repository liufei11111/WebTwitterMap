package com.web.map.app.model;

import java.util.HashMap;
import java.util.List;

public class GeocoderMap {
    HashMap<String,List<Double>> map;

    public HashMap<String, List<Double>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, List<Double>> map) {
        this.map = map;
    }

}
