package ui;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 主面板
 */
public class MainPanel extends JPanel {
    public MainPanel() {
        MigLayout layout = new MigLayout(
                "",
                "[center]",
                ""
        );
        this.setLayout(layout);
        this.setBorder(new EmptyBorder(8, 8, 0, 8));

        FilesPanel filesPanel = new FilesPanel();
        this.add(filesPanel, new CC()
                .wrap()
                .width("100%")
        );

        ConfigPanel configPanel = new ConfigPanel();
        this.add(configPanel, new CC()
                .wrap()
                .width("100%")
                .height("100%")
        );

        ControlBar controlBar = new ControlBar();
        this.add(BorderLayout.SOUTH, controlBar);
    }
}
