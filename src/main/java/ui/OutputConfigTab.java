package ui;

import com.formdev.flatlaf.extras.components.FlatScrollPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 输出配置页面
 */
public class OutputConfigTab extends FlatScrollPane {
    public OutputConfigTab() {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        panel.setLayout(layout);
        this.setViewportView(panel);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        CollapsePane collapsePane = new CollapsePane();
        collapsePane.setText("编码器设置");
        JPanel subPanel = new JPanel();
        JLabel label = new JLabel("test");
        subPanel.setBorder(new TitledBorder("Test border"));
        subPanel.add(label);
        collapsePane.setSubPanel(subPanel);
        panel.add(collapsePane);
    }
}
