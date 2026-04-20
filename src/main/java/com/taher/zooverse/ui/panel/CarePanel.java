package com.taher.zooverse.ui.panel;

import com.taher.zooverse.model.FeedingSchedule;
import com.taher.zooverse.model.HealthRecord;
import com.taher.zooverse.service.ZooService;
import com.taher.zooverse.util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarePanel extends JPanel {
    private final ZooService service;
    private final DefaultTableModel feedModel = new DefaultTableModel(new String[]{"Animal ID", "Time", "Food"}, 0);
    private final DefaultTableModel healthModel = new DefaultTableModel(new String[]{"Animal ID", "Date", "Notes"}, 0);

    public CarePanel(ZooService service) {
        this.service = service;
        setLayout(new GridLayout(1, 2, 12, 12));
        setBackground(Theme.BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(feedPane());
        add(healthPane());
        refresh();
    }

    private JPanel feedPane() {
        JPanel p = Theme.makeCardLayoutPanel(new BorderLayout(8, 8));
        JLabel t = new JLabel("🥗 Feeding Schedules"); t.setForeground(Theme.TEXT); t.setFont(new Font("SansSerif", Font.BOLD, 22));
        p.add(t, BorderLayout.NORTH);
        JTable table = new JTable(feedModel);
        Theme.styleTable(table);
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton add = new JButton("+ Add Feeding");
        Theme.styleButton(add);
        add.addActionListener(e -> {
            try {
                String aid = JOptionPane.showInputDialog(this, "Animal ID:");
                String time = JOptionPane.showInputDialog(this, "Time (HH:mm):");
                String food = JOptionPane.showInputDialog(this, "Food:");
                service.addFeedingSchedule(aid, time, food);
                refresh();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Could not add feeding entry: " + ex.getMessage());
            }
        });
        p.add(add, BorderLayout.SOUTH);
        return p;
    }

    private JPanel healthPane() {
        JPanel p = Theme.makeCardLayoutPanel(new BorderLayout(8, 8));
        JLabel t = new JLabel("🩺 Health Records"); t.setForeground(Theme.TEXT); t.setFont(new Font("SansSerif", Font.BOLD, 22));
        p.add(t, BorderLayout.NORTH);
        JTable table = new JTable(healthModel);
        Theme.styleTable(table);
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton add = new JButton("+ Add Health Note");
        Theme.styleButton(add);
        add.addActionListener(e -> {
            try {
                String aid = JOptionPane.showInputDialog(this, "Animal ID:");
                String notes = JOptionPane.showInputDialog(this, "Notes:");
                service.addHealthRecord(aid, notes);
                refresh();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Could not add health note: " + ex.getMessage());
            }
        });
        p.add(add, BorderLayout.SOUTH);
        return p;
    }

    public void refresh() {
        feedModel.setRowCount(0);
        for (FeedingSchedule f : service.feedingSchedules()) {
            feedModel.addRow(new Object[]{f.getAnimalId(), f.getTime(), f.getFood()});
        }

        healthModel.setRowCount(0);
        for (HealthRecord h : service.healthRecords()) {
            healthModel.addRow(new Object[]{h.getAnimalId(), h.getDate(), h.getNotes()});
        }
    }
}
