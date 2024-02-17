package ui;

import com.formdev.flatlaf.extras.components.*;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * 文件选择区域
 */
public class FilesPanel extends JPanel {

    public FilesPanel() {
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
        FlatLabel label1 = new FlatLabel();
        label1.setText("视频源路径");
        this.add(label1);

        /* 视频源路径输入框 */
        FlatTextField textField1 = new FlatTextField();
        this.add(textField1, new CC()
                .width("100%")
        );

        /* 视频源文件选择按钮 */
        FlatButton button1 = new FlatButton();
        button1.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        button1.setToolTipText("从文件中选择");
        this.add(button1);

        /* 视频源文件信息按钮 */
        FlatButton button4 = new FlatButton();
        button4.setText("!");
        button4.setToolTipText("查看源视频编码信息");
        button4.putClientProperty( "FlatLaf.style", "font: $h4.font" );
        this.add(button4);

        /* “编码”文本 */
        FlatLabel label2 = new FlatLabel();
        label2.setText("编码:");
        this.add(label2);

        /* 视频编码模式选择菜单 */
        EncodingComboBox comboBox1 = new EncodingComboBox(EncodeMode.COPY);
        comboBox1.setToolTipText("选择视频重编码方式");
        this.add(comboBox1, new CC()
                .wrap()
        );

        /* “音频源路径”文本 */
        FlatLabel label3 = new FlatLabel();
        label3.setText("音频源路径");
        this.add(label3);

        /* 音频源路径输入框 */
        FlatTextField textField2 = new FlatTextField();
        this.add(textField2, new CC()
                .width("100%")
        );

        /* 音频源文件选择按钮 */
        FlatButton button2 = new FlatButton();
        button2.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        button2.setToolTipText("从文件中选择");
        this.add(button2);

        /* 音频源文件信息按钮 */
        FlatButton button5 = new FlatButton();
        button5.setText("!");
        button5.setToolTipText("查看源音频编码信息");
        button5.putClientProperty( "FlatLaf.style", "font: $h4.font" );
        this.add(button5);

        /* “编码”文本 */
        FlatLabel label4 = new FlatLabel();
        label4.setText("编码:");
        this.add(label4);

        /* 音频编码模式选择菜单 */
        EncodingComboBox comboBox2 = new EncodingComboBox(EncodeMode.AUTO);
        comboBox2.setToolTipText("选择音频重编码方式");
        this.add(comboBox2, new CC()
                .wrap()
        );

        /* “输出路径”文本 */
        FlatLabel label5 = new FlatLabel();
        label5.setText("输出路径");
        this.add(label5);

        /* 输出路径输入框 */
        FlatTextField textField3 = new FlatTextField();
        this.add(textField3, new CC()
                .width("100%")
                .spanX(4)
        );

        /* 输出文件选择按钮 */
        FlatButton button3 = new FlatButton();
        button3.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        button3.setToolTipText("选择输出路径");
        this.add(button3);
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
