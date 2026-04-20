package com.taher.zooverse.ui.panel;

import com.taher.zooverse.model.Habitat;
import com.taher.zooverse.model.Zookeeper;
import com.taher.zooverse.service.ZooService;
import com.taher.zooverse.util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OperationsPanel extends JPanel {
    private final ZooService service;
    private final DefaultTableModel habitatModel = new DefaultTableModel(new String[]{"ID", "Name", "Climate", "Capacity"}, 0);
    private final DefaultTableModel keeperModel = new DefaultTableModel(new String[]{"ID", "Name", "Shift"}, 0);

    public OperationsPanel(ZooService service) {
        this.service = service;
        setLayout(new GridLayout(1, 2, 12, 12));
        setBackground(Theme.BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(buildHabitatPanel());
        add(buildKeeperPanel());
        refresh();
    }

    private JPanel buildHabitatPanel() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBackground(Theme.CARD);
        p.setBorder(new EmptyBorder(12, 12, 12, 12));
        JLabel t = new JLabel("Habitats"); t.setForeground(Theme.TEXT); t.setFont(new Font("SansSerif", Font.BOLD, 20));
        p.add(t, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(habitatModel)), BorderLayout.CENTER);
        JButton add = new JButton("Add Habitat");
        add.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Habitat ID:");
            String name = JOptionPane.showInputDialog(this, "Name:");
            String climate = JOptionPane.showInputDialog(this, "Climate:");
            int cap = Integer.parseInt(JOptionPane.showInputDialog(this, "Capacity:"));
            service.addHabitat(new Habitat(id, name, climate, cap));
            refresh();
        });
        p.add(add, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildKeeperPanel() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBackground(Theme.CARD);
        p.setBorder(new EmptyBorder(12, 12, 12, 12));
        JLabel t = new JLabel("Zookeepers"); t.setForeground(Theme.TEXT); t.setFont(new Font("SansSerif", Font.BOLD, 20));
        p.add(t, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(keeperModel)), BorderLayout.CENTER);
        JButton add = new JButton("Add Keeper");
        add.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Keeper ID:");
            String name = JOptionPane.showInputDialog(this, "Name:");
            String shift = JOptionPane.showInputDialog(this, "Shift:");
            service.addKeeper(new Zookeeper(id, name, shift));
            refresh();
        });
        p.add(add, BorderLayout.SOUTH);
        return p;
    }

    public void refresh() {
        habitatModel.setRowCount(0);
        service.habitats().forEach(h -> habitatModel.addRow(new Object[]{h.getId(), h.getName(), h.getClimate(), h.getCapacity()}));

        keeperModel.setRowCount(0);
        service.keepers().forEach(k -> keeperModel.addRow(new Object[]{k.getId(), k.getName(), k.getShift()}));
    }
}
