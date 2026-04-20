package com.taher.zooverse.model;

import java.io.Serializable;

public class Zookeeper implements Serializable {
    private final String id;
    private String name;
    private String shift;

    public Zookeeper(String id, String name, String shift) {
        this.id = id;
        this.name = name;
        this.shift = shift;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
}
