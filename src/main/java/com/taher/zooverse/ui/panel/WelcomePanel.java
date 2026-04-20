package com.taher.zooverse.ui.panel;

import com.taher.zooverse.util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private final JLabel pulseLabel = new JLabel("Tracking wildlife operations ●", SwingConstants.CENTER);
    private int pulseStep = 0;

    public WelcomePanel() {
        setLayout(new BorderLayout(16, 16));
        setBackground(Theme.BG);
        setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel hero = Theme.makeCardLayoutPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("🐾 Welcome to ZooVerse", SwingConstants.CENTER);
        title.setForeground(Theme.TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 38));

        JLabel subtitle = new JLabel("Premium Zoo Management Experience", SwingConstants.CENTER);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));

        pulseLabel.setForeground(Theme.ACCENT);
        pulseLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JPanel textStack = new JPanel(new GridLayout(3, 1, 6, 6));
        textStack.setOpaque(false);
        textStack.add(title);
        textStack.add(subtitle);
        textStack.add(pulseLabel);

        hero.add(textStack, BorderLayout.NORTH);

        JPanel iconRow = new JPanel(new GridLayout(1, 5, 12, 12));
        iconRow.setOpaque(false);
        iconRow.add(iconCard("🦁", "Big Cats"));
        iconRow.add(iconCard("🦓", "Safari"));
        iconRow.add(iconCard("🦜", "Aviary"));
        iconRow.add(iconCard("🐍", "Reptiles"));
        iconRow.add(iconCard("🐘", "Giants"));

        hero.add(iconRow, BorderLayout.CENTER);
        add(hero, BorderLayout.CENTER);

        Timer timer = new Timer(500, e -> animatePulse());
        timer.start();
    }

    private JPanel iconCard(String emoji, String labelText) {
        JPanel card = Theme.makeCardLayoutPanel(new BorderLayout(6, 6));
        JLabel icon = new JLabel(emoji, SwingConstants.CENTER);
        icon.setFont(new Font("SansSerif", Font.PLAIN, 40));
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setForeground(Theme.TEXT);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        card.add(icon, BorderLayout.CENTER);
        card.add(label, BorderLayout.SOUTH);
        return card;
    }

    private void animatePulse() {
        pulseStep = (pulseStep + 1) % 4;
        String dots = "●";
        if (pulseStep == 1) dots = "●○○";
        else if (pulseStep == 2) dots = "●●○";
        else if (pulseStep == 3) dots = "●●●";
        pulseLabel.setText("Tracking wildlife operations " + dots);
    }
}
