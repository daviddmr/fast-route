package br.com.trixlog.model;

/**
 * Created by kaynan on 19/01/17.
 */

public class Coordinate {

    private String formatedName;
    private Double lat;
    private Double lng;

    public Coordinate() {
    }

    public Coordinate(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Coordinate(String formatedName, Double lat, Double lng) {
        this.formatedName = formatedName;
        this.lat = lat;
        this.lng = lng;
    }

    public String getFormatedName() {
        return formatedName;
    }

    public void setFormatedName(String formatedName) {
        this.formatedName = formatedName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "formatedName='" + formatedName + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
