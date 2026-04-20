package com.taher.zooverse.model;

public class Bird extends Animal {
    public Bird(String id, String name, int age, Gender gender, DietType dietType, HealthStatus healthStatus) {
        super(id, name, age, gender, dietType, healthStatus);
    }

    @Override public String getSpecies() { return "Bird"; }
    @Override public String makeSound() { return "Chirp/Squawk"; }
    @Override public String feedingInstruction() { return "Provide grains/fruits twice daily and fresh water."; }
}
