package com.taher.zooverse.model;

import java.io.Serializable;

public class Habitat implements Serializable {
    private final String id;
    private String name;
    private String climate;
    private int capacity;

    public Habitat(String id, String name, String climate, int capacity) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getClimate() { return climate; }
    public void setClimate(String climate) { this.climate = climate; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
