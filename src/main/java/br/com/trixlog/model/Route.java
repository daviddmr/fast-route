package br.com.trixlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by kaynan on 19/01/17.
 */
public class Route {

    @Id
    private String id;
    private String name;
    private Long date;
    @DBRef
    private Vehicle vehicle;
    private List<Stop> stops;
    private List<Coordinate> coordinates;

    public Route() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", vehicle=" + vehicle +
                ", stops=" + stops +
                ", coordinates=" + coordinates +
                '}';
    }
}
