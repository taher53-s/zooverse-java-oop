package com.taher.zooverse.ui.panel;

import com.taher.zooverse.model.*;
import com.taher.zooverse.service.ZooService;
import com.taher.zooverse.util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AnimalsPanel extends JPanel {
    private final ZooService service;
    private final DefaultTableModel model;
    private final JTextField searchField = new JTextField();
    private final JComboBox<String> filterBox = new JComboBox<>(new String[]{"All", "Mammal", "Bird", "Reptile"});

    public AnimalsPanel(ZooService service) {
        this.service = service;
        setLayout(new BorderLayout(12, 12));
        setBackground(Theme.BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel top = new JPanel(new BorderLayout(8, 8));
        top.setOpaque(false);
        JLabel title = new JLabel("🦁 Animal Registry");
        title.setForeground(Theme.TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        top.add(title, BorderLayout.WEST);

        JPanel searchWrap = new JPanel(new GridLayout(1, 3, 8, 8));
        searchWrap.setOpaque(false);
        Theme.styleTextField(searchField);
        Theme.styleTextField(filterBox);
        searchWrap.add(searchField);
        searchWrap.add(filterBox);
        JButton refreshBtn = new JButton("Search/Refresh");
        Theme.styleButton(refreshBtn);
        refreshBtn.addActionListener(e -> refreshTable());
        searchWrap.add(refreshBtn);
        top.add(searchWrap, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Species", "Age", "Diet", "Health", "Habitat", "Keeper", "Sound"}, 0);
        JTable table = new JTable(model);
        Theme.styleTable(table);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        controls.setOpaque(false);
        JButton add = new JButton("+ Add Animal");
        JButton delete = new JButton("Delete by ID");
        JButton assign = new JButton("Assign Keeper");
        JButton move = new JButton("Move Habitat");
        Theme.styleButton(add);
        Theme.styleSecondaryButton(delete);
        Theme.styleSecondaryButton(assign);
        Theme.styleSecondaryButton(move);
        controls.add(add); controls.add(delete); controls.add(assign); controls.add(move);
        add(controls, BorderLayout.SOUTH);

        add.addActionListener(e -> addAnimal());
        delete.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Animal ID to delete:");
            if (id != null && !id.isBlank()) { service.deleteAnimal(id.trim()); refreshTable(); }
        });
        assign.addActionListener(e -> {
            String aid = JOptionPane.showInputDialog(this, "Animal ID:");
            String kid = JOptionPane.showInputDialog(this, "Keeper ID:");
            if (aid != null && kid != null) { service.assignKeeper(aid.trim(), kid.trim()); refreshTable(); }
        });
        move.addActionListener(e -> {
            String aid = JOptionPane.showInputDialog(this, "Animal ID:");
            String hid = JOptionPane.showInputDialog(this, "Habitat ID:");
            if (aid != null && hid != null) { service.moveAnimalToHabitat(aid.trim(), hid.trim()); refreshTable(); }
        });

        refreshTable();
    }

    private void addAnimal() {
        String[] types = {"Mammal", "Bird", "Reptile"};
        String type = (String) JOptionPane.showInputDialog(this, "Type:", "Add Animal", JOptionPane.PLAIN_MESSAGE, null, types, types[0]);
        if (type == null) return;

        try {
            String id = JOptionPane.showInputDialog(this, "ID (e.g., A10):");
            String name = JOptionPane.showInputDialog(this, "Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age:"));
            Gender gender = Gender.valueOf(JOptionPane.showInputDialog(this, "Gender MALE/FEMALE:").toUpperCase());
            DietType diet = DietType.valueOf(JOptionPane.showInputDialog(this, "Diet HERBIVORE/CARNIVORE/OMNIVORE:").toUpperCase());
            HealthStatus health = HealthStatus.valueOf(JOptionPane.showInputDialog(this, "Health HEALTHY/UNDER_OBSERVATION/SICK/RECOVERING:").toUpperCase());

            Animal animal = switch (type) {
                case "Bird" -> new Bird(id, name, age, gender, diet, health);
                case "Reptile" -> new Reptile(id, name, age, gender, diet, health);
                default -> new Mammal(id, name, age, gender, diet, health);
            };
            service.addAnimal(animal);
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Could not add animal: " + ex.getMessage());
        }
    }

    public void refreshTable() {
        model.setRowCount(0);
        String query = searchField.getText();
        String filter = (String) filterBox.getSelectedItem();
        for (Animal a : service.searchAnimals(query, filter)) {
            model.addRow(new Object[]{a.getId(), a.getName(), a.getSpecies(), a.getAge(), a.getDietType(), a.getHealthStatus(), a.getHabitatId(), a.getAssignedKeeperId(), a.makeSound()});
        }
    }
}
