# ZooVerse 🦁
A full Java OOP desktop project (Swing + Maven) for virtual zoo management.

## Why this topic?
It is unique, practical, and perfect for explaining OOP in viva.

## Features
- Animal management (add/delete/search/filter/sort)
- Habitat management
- Zookeeper management + assignment to animals
- Move animals to habitats with capacity checks
- Feeding schedule management
- Health records management
- Dashboard statistics
- Data persistence (serialized file)
- Sample data seeding

## OOP Concepts Used
- **Abstraction:** `Animal` is abstract.
- **Encapsulation:** private fields + getters/setters.
- **Inheritance:** `Mammal`, `Bird`, `Reptile` extend `Animal`.
- **Polymorphism:** `makeSound()` and `feedingInstruction()` are overridden.
- **Interface:** `Feedable` interface.
- **Composition:** `ZooData` contains animals/habitats/keepers/records/schedules.
- **Custom Exception:** `ZooException`.
- **Enums:** `DietType`, `HealthStatus`, `Gender`.

## Project Structure
```
src/main/java/com/taher/zooverse/
  model/
  service/
  repository/
  ui/
  util/
  exception/
```

## Run
```bash
cd ~/java-proj
mvn clean package
mvn exec:java
```

## One-command run
```bash
./run.sh
```

## Data file
Saved at: `~/java-proj/data/zooverse.ser`

## Viva Tips
- Show Animal hierarchy first
- Demonstrate adding an animal and assigning keeper
- Show habitat full validation (custom exception usage)
- Explain persistence with save/restart/load
