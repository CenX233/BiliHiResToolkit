package ui;

import com.formdev.flatlaf.extras.components.FlatTabbedPane;

import javax.swing.*;
import java.awt.*;

/**
 * 高级选项区域
 */
public class ConfigPanel extends JPanel {
    public ConfigPanel() {
        this.setPreferredSize(new Dimension(420, 0));

        FlatTabbedPane tabs = new FlatTabbedPane();
        tabs.setPreferredSize(new Dimension(400, 406));
        tabs.setShowTabSeparators(true);
        tabs.addTab("输出选项", new OutputConfigTab());
        tabs.addTab("音频选项", new Container());
        tabs.addTab("视频选项", new Container());
        this.add(tabs);
    }
}
