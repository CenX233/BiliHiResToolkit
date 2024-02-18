package ui.control;

import com.formdev.flatlaf.extras.components.FlatLabel;
import com.formdev.flatlaf.extras.components.FlatProgressBar;

import javax.swing.*;
import java.awt.*;

/**
 * 底部控制栏
 */
public class ControlBar extends JPanel {
    public ControlBar() {
        FlatLabel label = new FlatLabel();
        label.setText("底部控制栏区域");
        this.add(label);

        FlatProgressBar progressBar = new FlatProgressBar();
        progressBar.setPreferredSize(
                new Dimension(200, 12)
        );
        progressBar.setIndeterminate(true);
        progressBar.setStringPainted(true);
        progressBar.setString("114.514%");
        this.add(progressBar);
    }
}
