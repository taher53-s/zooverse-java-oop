package com.taher.zooverse.util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;

public final class Theme {
    public static final Color BG = new Color(14, 18, 24);
    public static final Color BG_SOFT = new Color(20, 27, 36);
    public static final Color CARD = new Color(30, 40, 52);
    public static final Color SIDEBAR = new Color(24, 32, 43);
    public static final Color ACCENT = new Color(62, 174, 128);
    public static final Color ACCENT_DARK = new Color(36, 134, 94);
    public static final Color TEXT = new Color(232, 241, 235);
    public static final Color MUTED = new Color(162, 177, 168);

    private Theme() {}

    public static void applyNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    public static void styleButton(JButton b) {
        b.setFocusPainted(false);
        b.setBackground(ACCENT);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new CompoundBorder(new RoundedBorder(12, ACCENT_DARK), new EmptyBorder(8, 14, 8, 14)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void styleSecondaryButton(JButton b) {
        b.setFocusPainted(false);
        b.setBackground(BG_SOFT);
        b.setForeground(TEXT);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new CompoundBorder(new RoundedBorder(12, new Color(63, 79, 94)), new EmptyBorder(8, 14, 8, 14)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void styleTextField(JComponent field) {
        field.setBackground(new Color(34, 46, 60));
        field.setForeground(TEXT);
        field.setBorder(new CompoundBorder(new RoundedBorder(10, new Color(72, 93, 110)), new EmptyBorder(8, 10, 8, 10)));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    public static void styleTable(JTable table) {
        table.setBackground(new Color(24, 32, 42));
        table.setForeground(TEXT);
        table.setSelectionBackground(new Color(52, 89, 77));
        table.setSelectionForeground(Color.WHITE);
        table.setRowHeight(28);
        table.setGridColor(new Color(50, 62, 76));
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(38, 50, 64));
        header.setForeground(TEXT);
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
    }

    public static JPanel makeCardLayoutPanel(LayoutManager layout) {
        JPanel p = new JPanel(layout);
        p.setBackground(CARD);
        p.setBorder(new CompoundBorder(new RoundedBorder(18, new Color(67, 84, 102)), new EmptyBorder(12, 12, 12, 12)));
        return p;
    }

    public static class RoundedBorder implements Border {
        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override public Insets getBorderInsets(Component c) { return new Insets(1, 1, 1, 1); }
        @Override public boolean isBorderOpaque() { return false; }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}
