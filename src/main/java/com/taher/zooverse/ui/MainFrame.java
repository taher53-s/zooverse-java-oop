package com.taher.zooverse.ui;

import com.taher.zooverse.service.ZooService;
import com.taher.zooverse.ui.panel.AnimalsPanel;
import com.taher.zooverse.ui.panel.CarePanel;
import com.taher.zooverse.ui.panel.DashboardPanel;
import com.taher.zooverse.ui.panel.OperationsPanel;
import com.taher.zooverse.util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel content = new JPanel(cardLayout);

    private final DashboardPanel dashboardPanel;
    private final AnimalsPanel animalsPanel;
    private final OperationsPanel operationsPanel;
    private final CarePanel carePanel;

    public MainFrame(ZooService service) {
        setTitle("ZooVerse - Virtual Zoo Management");
        setSize(1280, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dashboardPanel = new DashboardPanel(service);
        animalsPanel = new AnimalsPanel(service);
        operationsPanel = new OperationsPanel(service);
        carePanel = new CarePanel(service);

        content.add(dashboardPanel, "Dashboard");
        content.add(animalsPanel, "Animals");
        content.add(operationsPanel, "Operations");
        content.add(carePanel, "Care");

        JPanel sidebar = new JPanel(new GridLayout(8, 1, 8, 8));
        sidebar.setBackground(new Color(22, 27, 37));
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));

        JLabel logo = new JLabel("🦁 ZooVerse");
        logo.setForeground(Theme.TEXT);
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        sidebar.add(logo);

        addNavButton(sidebar, "Dashboard");
        addNavButton(sidebar, "Animals");
        addNavButton(sidebar, "Operations");
        addNavButton(sidebar, "Care");

        JButton saveBtn = new JButton("Save Data");
        saveBtn.addActionListener(e -> {
            service.persist();
            JOptionPane.showMessageDialog(this, "Saved successfully.");
            refreshAll();
        });
        sidebar.add(saveBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(content, BorderLayout.CENTER);
        getContentPane().setBackground(Theme.BG);
    }

    private void addNavButton(JPanel sidebar, String name) {
        JButton btn = new JButton(name);
        btn.setFocusPainted(false);
        btn.setBackground(Theme.ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.addActionListener(e -> {
            cardLayout.show(content, name);
            refreshAll();
        });
        sidebar.add(btn);
    }

    private void refreshAll() {
        dashboardPanel.refresh();
        animalsPanel.refreshTable();
        operationsPanel.refresh();
        carePanel.refresh();
    }
}
