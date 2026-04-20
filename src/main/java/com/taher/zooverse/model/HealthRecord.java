package com.taher.zooverse.model;

import java.io.Serializable;
import java.time.LocalDate;

public class HealthRecord implements Serializable {
    private final String animalId;
    private final LocalDate date;
    private final String notes;

    public HealthRecord(String animalId, LocalDate date, String notes) {
        this.animalId = animalId;
        this.date = date;
        this.notes = notes;
    }

    public String getAnimalId() { return animalId; }
    public LocalDate getDate() { return date; }
    public String getNotes() { return notes; }
}
