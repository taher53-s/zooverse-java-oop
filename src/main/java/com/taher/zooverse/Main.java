package com.taher.zooverse;

import com.taher.zooverse.repository.ZooRepository;
import com.taher.zooverse.service.ZooService;
import com.taher.zooverse.ui.MainFrame;
import com.taher.zooverse.util.Theme;

import javax.swing.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Theme.applyNimbus();
        SwingUtilities.invokeLater(() -> {
            ZooRepository repo = new ZooRepository(Path.of(System.getProperty("user.home"), "java-proj", "data", "zooverse.ser"));
            ZooService service = new ZooService(repo);
            new MainFrame(service).setVisible(true);
        });
    }
}
