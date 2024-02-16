package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 主面板
 */
public class MainPanel extends JPanel {
    public MainPanel() {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        FilesPanel filesPanel = new FilesPanel();
        filesPanel.setBorder(new EmptyBorder(2, 8, 0, 0));
        this.add(BorderLayout.CENTER, filesPanel);

        ConfigPanel configPanel = new ConfigPanel();
        this.add(BorderLayout.EAST, configPanel);

        ControlBar controlBar = new ControlBar();
        this.add(BorderLayout.SOUTH, controlBar);
    }
}
