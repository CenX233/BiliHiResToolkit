package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 高级选项区域
 */
public class ConfigPanel extends JPanel {
    public ConfigPanel() {
        this.setPreferredSize(new Dimension(400, 0));

        TitledBorder border = new TitledBorder("高级选项区域");
        this.setBorder(border);
    }
}
