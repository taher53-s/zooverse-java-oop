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
        setTitle("ZooVerse - Wild Operations Console");
        setSize(1320, 800);
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
        content.setBackground(Theme.BG);

        JPanel sidebar = new JPanel(new BorderLayout(10, 10));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(Theme.SIDEBAR);
        sidebar.setBorder(new EmptyBorder(20, 16, 20, 16));

        JLabel logo = new JLabel("🦁 ZooVerse");
        logo.setForeground(Theme.TEXT);
        logo.setFont(new Font("SansSerif", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Virtual Zoo Command Center");
        subtitle.setForeground(Theme.MUTED);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel top = new JPanel(new GridLayout(2, 1));
        top.setOpaque(false);
        top.add(logo);
        top.add(subtitle);
        sidebar.add(top, BorderLayout.NORTH);

        JPanel nav = new JPanel(new GridLayout(6, 1, 8, 8));
        nav.setOpaque(false);
        addNavButton(nav, "📊 Dashboard", "Dashboard");
        addNavButton(nav, "🦓 Animals", "Animals");
        addNavButton(nav, "🏞️ Operations", "Operations");
        addNavButton(nav, "🩺 Care & Feeding", "Care");
        sidebar.add(nav, BorderLayout.CENTER);

        JButton saveBtn = new JButton("💾 Save Zoo Data");
        Theme.styleButton(saveBtn);
        saveBtn.addActionListener(e -> {
            service.persist();
            JOptionPane.showMessageDialog(this, "ZooVerse data saved successfully.");
            refreshAll();
        });
        sidebar.add(saveBtn, BorderLayout.SOUTH);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Theme.BG);
        root.add(sidebar, BorderLayout.WEST);
        root.add(content, BorderLayout.CENTER);

        setContentPane(root);
    }

    private void addNavButton(JPanel sidebar, String label, String route) {
        JButton btn = new JButton(label);
        Theme.styleSecondaryButton(btn);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.addActionListener(e -> {
            cardLayout.show(content, route);
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
