package com.taher.zooverse.model;

import java.io.Serializable;

public abstract class Animal implements Feedable, Serializable {
    private final String id;
    private String name;
    private int age;
    private Gender gender;
    private DietType dietType;
    private HealthStatus healthStatus;
    private String habitatId;
    private String assignedKeeperId;

    protected Animal(String id, String name, int age, Gender gender, DietType dietType, HealthStatus healthStatus) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dietType = dietType;
        this.healthStatus = healthStatus;
    }

    public abstract String getSpecies();
    public abstract String makeSound();

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public DietType getDietType() { return dietType; }
    public void setDietType(DietType dietType) { this.dietType = dietType; }
    public HealthStatus getHealthStatus() { return healthStatus; }
    public void setHealthStatus(HealthStatus healthStatus) { this.healthStatus = healthStatus; }
    public String getHabitatId() { return habitatId; }
    public void setHabitatId(String habitatId) { this.habitatId = habitatId; }
    public String getAssignedKeeperId() { return assignedKeeperId; }
    public void setAssignedKeeperId(String assignedKeeperId) { this.assignedKeeperId = assignedKeeperId; }
}
