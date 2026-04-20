package com.taher.zooverse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ZooData implements Serializable {
    private List<Animal> animals = new ArrayList<>();
    private List<Habitat> habitats = new ArrayList<>();
    private List<Zookeeper> keepers = new ArrayList<>();
    private List<HealthRecord> healthRecords = new ArrayList<>();
    private List<FeedingSchedule> feedingSchedules = new ArrayList<>();

    public List<Animal> getAnimals() { return animals; }
    public void setAnimals(List<Animal> animals) { this.animals = animals; }
    public List<Habitat> getHabitats() { return habitats; }
    public void setHabitats(List<Habitat> habitats) { this.habitats = habitats; }
    public List<Zookeeper> getKeepers() { return keepers; }
    public void setKeepers(List<Zookeeper> keepers) { this.keepers = keepers; }
    public List<HealthRecord> getHealthRecords() { return healthRecords; }
    public void setHealthRecords(List<HealthRecord> healthRecords) { this.healthRecords = healthRecords; }
    public List<FeedingSchedule> getFeedingSchedules() { return feedingSchedules; }
    public void setFeedingSchedules(List<FeedingSchedule> feedingSchedules) { this.feedingSchedules = feedingSchedules; }
}
