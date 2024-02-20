package ui.control;

import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.extras.components.FlatCheckBox;
import com.formdev.flatlaf.extras.components.FlatProgressBar;
import com.formdev.flatlaf.util.SystemInfo;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import ui.RemuxConfigStore;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * 底部控制栏
 */
public class ControlBar extends JPanel {
    public ControlBar() {
        MigLayout layout = new MigLayout();
        this.setLayout(layout);

        /* 开始按钮 */
        FlatButton controlButton = new FlatButton();
        controlButton.setText("开始");
        this.add(controlButton);

        /* 打开文件夹按钮 */
        FlatButton openDirButton = new FlatButton();
        openDirButton.setText("打开输出文件夹");
        openDirButton.addActionListener(e -> {
            this.openDir(RemuxConfigStore.getOutputFile());
        });
        this.add(openDirButton);

        /* 完成后打开文件夹复选框 */
        FlatCheckBox openDirCheckBox = new FlatCheckBox();
        openDirCheckBox.setText("完成后打开文件夹");
        this.add(openDirCheckBox);

        /* 进度条 */
        FlatProgressBar progressBar = new FlatProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("未开始...");
        this.add(progressBar, new CC()
                .width("100%")
        );
    }

    /**
     * 打开特定于操作系统的文件管理器
     * @param file 要打开的目标文件
     */
    private void openDir(File file) {
        if (file.exists()) {
            if (SystemInfo.isWindows) {
                try {
                    Runtime.getRuntime().exec(
                            "explorer.exe /select," + file.getAbsolutePath()
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "此功能未适配当前平台",
                        "错误",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "文件或文件夹不存在",
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}