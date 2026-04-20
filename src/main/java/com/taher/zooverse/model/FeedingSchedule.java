package com.taher.zooverse.model;

import java.io.Serializable;

public class FeedingSchedule implements Serializable {
    private final String animalId;
    private final String time;
    private final String food;

    public FeedingSchedule(String animalId, String time, String food) {
        this.animalId = animalId;
        this.time = time;
        this.food = food;
    }

    public String getAnimalId() { return animalId; }
    public String getTime() { return time; }
    public String getFood() { return food; }
}
