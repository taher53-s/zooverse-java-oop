package com.taher.zooverse.repository;

import com.taher.zooverse.model.ZooData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ZooRepository {
    private final Path dataPath;

    public ZooRepository(Path dataPath) {
        this.dataPath = dataPath;
    }

    public ZooData load() {
        try {
            if (!Files.exists(dataPath)) return new ZooData();
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataPath.toFile()))) {
                return (ZooData) in.readObject();
            }
        } catch (Exception e) {
            return new ZooData();
        }
    }

    public void save(ZooData data) {
        try {
            Files.createDirectories(dataPath.getParent());
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataPath.toFile()))) {
                out.writeObject(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not save zoo data", e);
        }
    }
}
