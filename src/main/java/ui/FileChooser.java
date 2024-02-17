package ui;

import javax.swing.*;

public class FileChooser extends JFileChooser {
    private final JFrame fileChooserFrame = new JFrame("选择文件");

    public int showOpenDialog() {
        return super.showOpenDialog(fileChooserFrame);
    }

    public int showSaveDialog() {
        return super.showSaveDialog(fileChooserFrame);
    }
}
