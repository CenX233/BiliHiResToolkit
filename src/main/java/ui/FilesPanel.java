package ui;

import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatComboBox;
import com.formdev.flatlaf.extras.components.FlatLabel;
import com.formdev.flatlaf.extras.components.FlatTextField;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * 文件选择区域
 */
public class FilesPanel extends JPanel {

    public FilesPanel() {
        FileChooser fileChooser = new FileChooser();
        MigLayout layout = new MigLayout(
                "",
                "left",
                ""
        );
        this.setLayout(layout);

        /* 边框 */
        TitledBorder title = new TitledBorder("基础设置");
        this.setBorder(title);

        /* “视频源路径”文本 */
        FlatLabel videoPathLabel = new FlatLabel();
        videoPathLabel.setText("视频源路径");
        this.add(videoPathLabel);

        /* 视频源路径输入框 */
        FlatTextField videoPathTextField = new FlatTextField();
        this.add(videoPathTextField, new CC()
                .width("100%")
        );

        /* 视频源文件选择按钮 */
        FlatButton videoSelectButton = new FlatButton();
        videoSelectButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        videoSelectButton.setToolTipText("从文件中选择");
        videoSelectButton.addActionListener(e -> {
            if (fileChooser.showOpenDialog(Mode.OPEN_VIDEO) == JFileChooser.FILES_ONLY) {
                videoPathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        this.add(videoSelectButton);

        /* 视频源文件信息按钮 */
        FlatButton videoInfoButton = new FlatButton();
        videoInfoButton.setText("!");
        videoInfoButton.setToolTipText("查看源视频编码信息");
        videoInfoButton.putClientProperty( "FlatLaf.style", "font: $h4.font" );
        this.add(videoInfoButton);

        /* “编码”文本 */
        FlatLabel videoEncodeLabel = new FlatLabel();
        videoEncodeLabel.setText("编码:");
        this.add(videoEncodeLabel);

        /* 视频编码模式选择菜单 */
        EncodingComboBox videoEncodeModeComboBox = new EncodingComboBox(EncodeMode.COPY);
        videoEncodeModeComboBox.setToolTipText("选择视频重编码方式");
        this.add(videoEncodeModeComboBox, new CC()
                .wrap()
        );

        /* “音频源路径”文本 */
        FlatLabel audioPathLabel = new FlatLabel();
        audioPathLabel.setText("音频源路径");
        this.add(audioPathLabel);

        /* 音频源路径输入框 */
        FlatTextField audioPathTextField = new FlatTextField();
        this.add(audioPathTextField, new CC()
                .width("100%")
        );

        /* 音频源文件选择按钮 */
        FlatButton audioPathSelectButton = new FlatButton();
        audioPathSelectButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        audioPathSelectButton.setToolTipText("从文件中选择");
        audioPathSelectButton.addActionListener(e -> {
            if (fileChooser.showOpenDialog(Mode.OPEN_AUDIO) == JFileChooser.FILES_ONLY) {
                audioPathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        this.add(audioPathSelectButton);

        /* 音频源文件信息按钮 */
        FlatButton audioInfoButton = new FlatButton();
        audioInfoButton.setText("!");
        audioInfoButton.setToolTipText("查看源音频编码信息");
        audioInfoButton.putClientProperty( "FlatLaf.style", "font: $h4.font" );
        this.add(audioInfoButton);

        /* “编码”文本 */
        FlatLabel audioEncodeLabel = new FlatLabel();
        audioEncodeLabel.setText("编码:");
        this.add(audioEncodeLabel);

        /* 音频编码模式选择菜单 */
        EncodingComboBox audioEncodeModeComboBox = new EncodingComboBox(EncodeMode.AUTO);
        audioEncodeModeComboBox.setToolTipText("选择音频重编码方式");
        this.add(audioEncodeModeComboBox, new CC()
                .wrap()
        );

        /* “输出路径”文本 */
        FlatLabel outputPathLabel = new FlatLabel();
        outputPathLabel.setText("输出路径");
        this.add(outputPathLabel);

        /* 输出路径输入框 */
        FlatTextField outputPathTextField = new FlatTextField();
        this.add(outputPathTextField, new CC()
                .width("100%")
                .spanX(4)
        );

        /* 输出文件选择按钮 */
        FlatButton outputPathSelectButton = new FlatButton();
        outputPathSelectButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        outputPathSelectButton.setToolTipText("选择输出路径");
        outputPathSelectButton.addActionListener(e -> {
            if (fileChooser.showSaveDialog(Mode.SAVE_VIDEO) == JFileChooser.FILES_ONLY) {
                outputPathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        this.add(outputPathSelectButton);
    }
}

class EncodingComboBox extends FlatComboBox<String> {
    public EncodingComboBox(EncodeMode mode) {
        this.addItem(EncodeMode.COPY.toString());
        this.addItem(EncodeMode.AUTO.toString());
        this.addItem(EncodeMode.CUSTOM.toString());
        this.setSelectedItem(mode.toString());
    }
}

enum EncodeMode {
    COPY("复制"),
    AUTO("自动"),
    CUSTOM("自定义");

    private final String string;
    EncodeMode(String s) {
        this.string = s;
    }
    @Override
    public String toString() {
        return this.string;
    }
}
