package ui;

import com.formdev.flatlaf.extras.components.FlatTabbedPane;

import javax.swing.*;
import java.awt.*;

/**
 * 高级选项区域
 */
public class ConfigPanel extends FlatTabbedPane {
    public ConfigPanel() {
        this.setShowTabSeparators(true);
        this.addTab("输出选项", new OutputConfigTab());
        this.addTab("音频选项", new Container());
        this.addTab("视频选项", new Container());
    }
}
