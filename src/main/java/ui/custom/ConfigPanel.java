package ui.custom;

import com.formdev.flatlaf.extras.components.FlatTabbedPane;
import ui.custom.output.OutputConfigTab;

import java.awt.*;

/**
 * 自定义选项区域
 */
public class ConfigPanel extends FlatTabbedPane {
    public ConfigPanel() {
        this.setShowTabSeparators(true);
        this.addTab("输出选项", new OutputConfigTab());
        this.addTab("音频选项", new Container());
        this.addTab("视频选项", new Container());
    }
}
