package com.taher.zooverse.ui.panel;

import com.taher.zooverse.service.ZooService;
import com.taher.zooverse.util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class DashboardPanel extends JPanel {
    private final ZooService service;
    private final JPanel cards = new JPanel(new GridLayout(2, 3, 16, 16));

    public DashboardPanel(ZooService service) {
        this.service = service;
        setLayout(new BorderLayout());
        setBackground(Theme.BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("ZooVerse Dashboard");
        title.setForeground(Theme.TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        cards.setBackground(Theme.BG);
        add(cards, BorderLayout.CENTER);
        refresh();
    }

    public void refresh() {
        cards.removeAll();
        for (Map.Entry<String, Long> entry : service.dashboardStats().entrySet()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Theme.CARD);
            card.setBorder(new EmptyBorder(16, 16, 16, 16));

            JLabel key = new JLabel(entry.getKey());
            key.setForeground(Theme.TEXT);
            key.setFont(new Font("SansSerif", Font.PLAIN, 16));

            JLabel value = new JLabel(String.valueOf(entry.getValue()));
            value.setForeground(Theme.ACCENT);
            value.setFont(new Font("SansSerif", Font.BOLD, 36));

            card.add(key, BorderLayout.NORTH);
            card.add(value, BorderLayout.CENTER);
            cards.add(card);
        }
        revalidate();
        repaint();
    }
}
