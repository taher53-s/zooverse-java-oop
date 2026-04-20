package com.taher.zooverse.model;

public class Mammal extends Animal {
    public Mammal(String id, String name, int age, Gender gender, DietType dietType, HealthStatus healthStatus) {
        super(id, name, age, gender, dietType, healthStatus);
    }

    @Override public String getSpecies() { return "Mammal"; }
    @Override public String makeSound() { return "Roar/Growl"; }
    @Override public String feedingInstruction() { return "Feed 2 times/day based on species diet."; }
}
