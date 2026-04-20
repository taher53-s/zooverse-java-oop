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

        JPanel heading = new JPanel(new GridLayout(2, 1));
        heading.setOpaque(false);
        JLabel title = new JLabel("🌿 ZooVerse Dashboard");
        title.setForeground(Theme.TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        JLabel subtitle = new JLabel("Live overview of animals, staff, and care operations");
        subtitle.setForeground(Theme.MUTED);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        heading.add(title);
        heading.add(subtitle);
        add(heading, BorderLayout.NORTH);

        cards.setBackground(Theme.BG);
        add(cards, BorderLayout.CENTER);
        refresh();
    }

    public void refresh() {
        cards.removeAll();
        for (Map.Entry<String, Long> entry : service.dashboardStats().entrySet()) {
            JPanel card = Theme.makeCardLayoutPanel(new BorderLayout());

            JLabel key = new JLabel(entry.getKey());
            key.setForeground(Theme.TEXT);
            key.setFont(new Font("SansSerif", Font.PLAIN, 16));

            JLabel value = new JLabel(String.valueOf(entry.getValue()));
            value.setForeground(Theme.ACCENT);
            value.setFont(new Font("SansSerif", Font.BOLD, 42));

            JLabel hint = new JLabel("Updated in real-time");
            hint.setForeground(Theme.MUTED);
            hint.setFont(new Font("SansSerif", Font.ITALIC, 12));

            card.add(key, BorderLayout.NORTH);
            card.add(value, BorderLayout.CENTER);
            card.add(hint, BorderLayout.SOUTH);
            cards.add(card);
        }
        revalidate();
        repaint();
    }
}
