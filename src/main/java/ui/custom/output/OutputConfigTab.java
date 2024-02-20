package ui.custom.output;

import com.formdev.flatlaf.extras.components.FlatScrollPane;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import ui.custom.CollapsePane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 输出配置页面
 */
public class OutputConfigTab extends FlatScrollPane {
    public OutputConfigTab() {
        JPanel panel = new JPanel();
        MigLayout layout = new MigLayout();
        panel.setLayout(layout);
        this.setViewportView(panel);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        BasicConfigPane basicConfigPane = new BasicConfigPane();
        panel.add(basicConfigPane, new CC()
                .width("97.2%")
                .wrap()
        );

//        CollapsePane collapsePane = new CollapsePane();
//        collapsePane.setText("封装格式设置");
//        JPanel subPanel = new JPanel();
//        JLabel label = new JLabel("test");
//        subPanel.setBorder(new TitledBorder("Test border"));
//        subPanel.add(label);
//        collapsePane.setSubPanel(subPanel);
//        panel.add(collapsePane, new CC()
//                .width("97.2%")
//                .wrap());
    }
}
