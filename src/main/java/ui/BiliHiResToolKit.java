package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class BiliHiResToolKit {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> {
            JFrame mainWindow = new JFrame("BiliHiResToolKit");
            mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainWindow.setSize(960, 480);
            mainWindow.setLocationRelativeTo(null);
            mainWindow.setResizable(false);
            mainWindow.setIconImage(
                    new FlatSVGIcon("icon.svg").getImage()
            );

            mainWindow.setJMenuBar(new TopMenuBar());

            Container container = mainWindow.getContentPane();
            container.add(new MainPanel());

            mainWindow.setVisible(true);
        });
    }
}
