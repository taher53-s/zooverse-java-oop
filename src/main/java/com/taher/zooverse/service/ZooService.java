package com.taher.zooverse.service;

import com.taher.zooverse.exception.ZooException;
import com.taher.zooverse.model.*;
import com.taher.zooverse.repository.ZooRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ZooService {
    private final ZooRepository repo;
    private final ZooData data;

    public ZooService(ZooRepository repo) {
        this.repo = repo;
        this.data = repo.load();
        if (data.getAnimals().isEmpty() && data.getHabitats().isEmpty()) seedSampleData();
    }

    public List<Animal> animals() { return data.getAnimals(); }
    public List<Habitat> habitats() { return data.getHabitats(); }
    public List<Zookeeper> keepers() { return data.getKeepers(); }
    public List<HealthRecord> healthRecords() { return data.getHealthRecords(); }
    public List<FeedingSchedule> feedingSchedules() { return data.getFeedingSchedules(); }

    public void addAnimal(Animal animal) {
        boolean exists = data.getAnimals().stream().anyMatch(a -> a.getId().equalsIgnoreCase(animal.getId()));
        if (exists) throw new ZooException("Animal ID already exists.");
        data.getAnimals().add(animal);
        persist();
    }

    public void deleteAnimal(String id) {
        data.getAnimals().removeIf(a -> a.getId().equalsIgnoreCase(id));
        persist();
    }

    public List<Animal> searchAnimals(String query, String speciesFilter) {
        return data.getAnimals().stream()
                .filter(a -> query == null || query.isBlank() || a.getName().toLowerCase().contains(query.toLowerCase()) || a.getId().toLowerCase().contains(query.toLowerCase()))
                .filter(a -> speciesFilter == null || speciesFilter.equals("All") || a.getSpecies().equalsIgnoreCase(speciesFilter))
                .sorted(Comparator.comparing(Animal::getName))
                .collect(Collectors.toList());
    }

    public void addHabitat(Habitat habitat) {
        data.getHabitats().add(habitat);
        persist();
    }

    public void addKeeper(Zookeeper keeper) {
        data.getKeepers().add(keeper);
        persist();
    }

    public void assignKeeper(String animalId, String keeperId) {
        Animal animal = requireAnimal(animalId);
        boolean keeperExists = data.getKeepers().stream().anyMatch(k -> k.getId().equals(keeperId));
        if (!keeperExists) throw new ZooException("Keeper not found.");
        animal.setAssignedKeeperId(keeperId);
        persist();
    }

    public void moveAnimalToHabitat(String animalId, String habitatId) {
        Animal animal = requireAnimal(animalId);
        Habitat habitat = data.getHabitats().stream().filter(h -> h.getId().equals(habitatId)).findFirst()
                .orElseThrow(() -> new ZooException("Habitat not found."));

        long occupants = data.getAnimals().stream().filter(a -> habitatId.equals(a.getHabitatId())).count();
        if (occupants >= habitat.getCapacity()) throw new ZooException("Habitat is full.");

        animal.setHabitatId(habitatId);
        persist();
    }

    public void addFeedingSchedule(String animalId, String time, String food) {
        requireAnimal(animalId);
        data.getFeedingSchedules().add(new FeedingSchedule(animalId, time, food));
        persist();
    }

    public void addHealthRecord(String animalId, String notes) {
        requireAnimal(animalId);
        data.getHealthRecords().add(new HealthRecord(animalId, LocalDate.now(), notes));
        persist();
    }

    public Map<String, Long> dashboardStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        stats.put("Total Animals", (long) data.getAnimals().size());
        stats.put("Habitats", (long) data.getHabitats().size());
        stats.put("Zookeepers", (long) data.getKeepers().size());
        stats.put("Health Records", (long) data.getHealthRecords().size());
        stats.put("Feeding Entries", (long) data.getFeedingSchedules().size());
        return stats;
    }

    public void persist() { repo.save(data); }

    private Animal requireAnimal(String animalId) {
        return data.getAnimals().stream().filter(a -> a.getId().equals(animalId)).findFirst()
                .orElseThrow(() -> new ZooException("Animal not found."));
    }

    public void seedSampleData() {
        data.getHabitats().addAll(List.of(
                new Habitat("H1", "Savannah Zone", "Warm", 4),
                new Habitat("H2", "Rainforest Dome", "Humid", 3),
                new Habitat("H3", "Reptile House", "Tropical", 5)
        ));

        data.getKeepers().addAll(List.of(
                new Zookeeper("K1", "Ayaan", "Morning"),
                new Zookeeper("K2", "Sara", "Evening")
        ));

        Animal lion = new Mammal("A1", "Zuri", 5, Gender.FEMALE, DietType.CARNIVORE, HealthStatus.HEALTHY);
        Animal parrot = new Bird("A2", "Milo", 2, Gender.MALE, DietType.OMNIVORE, HealthStatus.HEALTHY);
        Animal python = new Reptile("A3", "Naga", 4, Gender.MALE, DietType.CARNIVORE, HealthStatus.UNDER_OBSERVATION);

        lion.setHabitatId("H1"); lion.setAssignedKeeperId("K1");
        parrot.setHabitatId("H2"); parrot.setAssignedKeeperId("K2");
        python.setHabitatId("H3"); python.setAssignedKeeperId("K1");

        data.getAnimals().addAll(List.of(lion, parrot, python));
        data.getFeedingSchedules().add(new FeedingSchedule("A1", "09:00", "Fresh meat"));
        data.getFeedingSchedules().add(new FeedingSchedule("A2", "10:30", "Seeds + fruits"));
        data.getHealthRecords().add(new HealthRecord("A3", LocalDate.now().minusDays(1), "Minor skin shedding issue; under observation."));
        persist();
    }
}
