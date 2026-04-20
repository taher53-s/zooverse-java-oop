package com.taher.zooverse.util;

import javax.swing.*;
import java.awt.*;

public final class Theme {
    public static final Color BG = new Color(18, 22, 30);
    public static final Color CARD = new Color(28, 34, 45);
    public static final Color ACCENT = new Color(88, 166, 255);
    public static final Color TEXT = new Color(230, 237, 243);

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
}
