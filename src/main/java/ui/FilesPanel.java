package ui;

import javax.swing.*;
import java.awt.*;

/**
 * 文件选择区域
 */
public class FilesPanel extends JPanel {
    public FilesPanel() {

        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);

        VideoFilePane videoFilePane = new VideoFilePane();
        this.add(videoFilePane);

        AudioFilePane audioFilePane = new AudioFilePane();
        this.add(audioFilePane);

        OutputPane outputPane = new OutputPane();
        this.add(outputPane);
    }
}
