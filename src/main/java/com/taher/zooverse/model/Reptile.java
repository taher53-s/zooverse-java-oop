package com.taher.zooverse.model;

public class Reptile extends Animal {
    public Reptile(String id, String name, int age, Gender gender, DietType dietType, HealthStatus healthStatus) {
        super(id, name, age, gender, dietType, healthStatus);
    }

    @Override public String getSpecies() { return "Reptile"; }
    @Override public String makeSound() { return "Hiss"; }
    @Override public String feedingInstruction() { return "Feed in controlled quantity, maintain warmth."; }
}
