package ui.custom.info;

import com.formdev.flatlaf.extras.components.FlatScrollPane;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/* 媒体信息面板 */
public class MediaInfoTab extends FlatScrollPane {
    public MediaInfoTab() {
        JPanel panel = new JPanel();
        MigLayout layout = new MigLayout();
        panel.setLayout(layout);
        this.setViewportView(panel);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        AudioInfoPane audioInfoPane = new AudioInfoPane();
        panel.add(audioInfoPane, new CC()
                .width("97.2%")
                .wrap()
        );

        VideoInfoPane videoInfoPane = new VideoInfoPane();
        panel.add(videoInfoPane, new CC()
                .width("97.2%")
                .wrap()
        );
    }
}
