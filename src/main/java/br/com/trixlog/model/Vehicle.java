package br.com.trixlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kaynan on 19/01/17.
 */

@Document(collection = "vehicle")
public class Vehicle {
    @Id
    private String id;
    private String name;

    public Vehicle() {
    }

    public Vehicle(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
