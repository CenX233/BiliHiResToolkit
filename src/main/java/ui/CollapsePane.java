package ui;

import com.formdev.flatlaf.extras.components.FlatButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 可折叠面板
 */
public class CollapsePane extends JPanel {
    private final FlatButton button = new FlatButton();
    private JPanel panel;
    private boolean isCollapsed = false;

    public CollapsePane() {
        this.init();
    }

    public CollapsePane(JPanel panel) {
        this.panel = panel;
        this.init();
    }

    private void init() {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setBorder(new EmptyBorder(4, 2, 4, 2));

        if (this.panel != null) {
            this.setVisible(false);
            this.add(BorderLayout.CENTER, this.panel);
        }

        this.button.setHorizontalAlignment(SwingUtilities.LEFT);
        this.button.setButtonType(FlatButton.ButtonType.square);
        this.button.setIcon(UIManager.getIcon("Tree.expandedIcon"));
        this.button.setPreferredSize(
                new Dimension(this.getWidth(), 18)
        );
        this.button.addActionListener(e -> {
                    this.setCollapsed(!this.isCollapsed);
                }
        );
        this.add(BorderLayout.NORTH, this.button);
    }

    public void setText(String text) {
        this.button.setText(text);
    }

    public String getText() {
        return this.button.getText();
    }

    /**
     * 获取子面板
     * @return 子面板
     */
    public JPanel getSubPanel() {
        return this.panel;
    }

    /**
     * 设置子面板
     * @param panel 子面板
     */
    public void setSubPanel(JPanel panel) {
        if (this.panel != null) {
            this.remove(this.panel);
        }
        if (panel != null) {
            if (this.isCollapsed()) {
                panel.setVisible(false);
            } else {
                panel.setVisible(true);
            }
            this.panel = panel;
            this.add(BorderLayout.CENTER, this.panel);
        }
    }

    /**
     * 获取面板是否折叠
     * @return true表示已折叠
     */
    public boolean isCollapsed() {
        return this.isCollapsed;
    }

    /**
     * 设置面板是否折叠
     * @param collapsed 是否折叠
     */
    public void setCollapsed(boolean collapsed) {
        if (this.isCollapsed != collapsed) {
            if (collapsed) {
                if (this.panel != null) {
                    this.panel.setVisible(false);
                }
                this.button.setIcon(UIManager.getIcon("Tree.collapsedIcon"));
                this.isCollapsed = true;
            } else {
                if (this.panel != null) {
                    this.panel.setVisible(true);
                }
                this.button.setIcon(UIManager.getIcon("Tree.expandedIcon"));
                this.isCollapsed = false;
            }
        }
    }
}
