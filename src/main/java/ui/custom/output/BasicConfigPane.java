package ui.custom.output;

import com.formdev.flatlaf.extras.components.FlatCheckBox;
import com.formdev.flatlaf.extras.components.FlatComboBox;
import com.formdev.flatlaf.extras.components.FlatLabel;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import ui.custom.CollapsePane;

import javax.swing.*;
import java.text.NumberFormat;

public class BasicConfigPane extends CollapsePane {
    public BasicConfigPane() {
        JPanel panel = new JPanel();
        MigLayout layout = new MigLayout();
        panel.setLayout(layout);
        this.setSubPanel(panel);
        this.setText("基础选项");

        /* “视频偏移时长”文本 */
        FlatLabel videoOffsetLabel = new FlatLabel();
        videoOffsetLabel.setText("视频偏移时长:");
        panel.add(videoOffsetLabel);

        /* 视频偏移时长输入框 */
        JFormattedTextField videoOffsetTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        videoOffsetTextField.setValue(0);
        videoOffsetTextField.setToolTipText("视频流偏移时长\n可以是正或负的整数");
        panel.add(videoOffsetTextField, new CC()
                .width("15%")
        );

        /* “毫秒”文本 */
        FlatLabel videoMillisecondLabel = new FlatLabel();
        videoMillisecondLabel.setText("毫秒");
        panel.add(videoMillisecondLabel, new CC()
                .wrap()
        );

        /* “音频偏移时长”文本 */
        FlatLabel audioOffsetLabel = new FlatLabel();
        audioOffsetLabel.setText("音频偏移时长:");
        panel.add(audioOffsetLabel);

        /* 音频偏移时长输入框 */
        JFormattedTextField audioOffsetTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        audioOffsetTextField.setValue(0);
        audioOffsetTextField.setToolTipText("音频流偏移时长\n可以是正或负的整数");
        panel.add(audioOffsetTextField, new CC()
                .width("15%")
        );

        /* “毫秒”文本 */
        FlatLabel audioMillisecondLabel = new FlatLabel();
        audioMillisecondLabel.setText("毫秒");
        panel.add(audioMillisecondLabel, new CC()
                .wrap()
        );

        /* 以最短的流作为输出长度复选框 */
        FlatCheckBox shortestCheckBox = new FlatCheckBox();
        FlatCheckBox apadCheckBox = new FlatCheckBox();
        shortestCheckBox.setText("以最短的流作为输出长度");
        shortestCheckBox.addActionListener(e -> {
            if (shortestCheckBox.isSelected()) {
                apadCheckBox.setEnabled(true);
            } else {
                apadCheckBox.setSelected(false);
                apadCheckBox.setEnabled(false);
            }
        });
        panel.add(shortestCheckBox, new CC()
                .spanX(3)
                .wrap()
        );

        apadCheckBox.setEnabled(false);
        apadCheckBox.setText("允许填充音频以适应输出长度");
        panel.add(apadCheckBox, new CC()
                .spanX(3)
                .wrap()
        );
    }
}
