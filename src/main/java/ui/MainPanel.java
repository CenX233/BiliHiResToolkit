package ui;

import javax.swing.*;
import java.awt.*;

/**
 * 主面板
 */
public class MainPanel extends JPanel {
    public MainPanel() {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        FilesPanel filesPanel = new FilesPanel();
        this.add(BorderLayout.CENTER, filesPanel);

        ConfigPanel configPanel = new ConfigPanel();
        this.add(BorderLayout.EAST, configPanel);

        ControlBar controlBar = new ControlBar();
        this.add(BorderLayout.SOUTH, controlBar);
    }
}
