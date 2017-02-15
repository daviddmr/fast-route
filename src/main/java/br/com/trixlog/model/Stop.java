package br.com.trixlog.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kaynan on 19/01/17.
 */
@Document(collection = "stops")
public class Stop {

    private String name;
    private Coordinate coordinate;

    public Stop() {
    }

    public Stop(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "name='" + name + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
