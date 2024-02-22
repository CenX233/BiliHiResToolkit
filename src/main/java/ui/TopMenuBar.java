package ui;

import com.formdev.flatlaf.extras.components.FlatMenu;
import com.formdev.flatlaf.extras.components.FlatMenuBar;
import com.formdev.flatlaf.extras.components.FlatMenuItem;

/**
 * 顶部工具栏
 */
public class TopMenuBar extends FlatMenuBar {
    public TopMenuBar() {

        FlatMenu settingMenu = new FlatMenu();
        settingMenu.setText("设置");
        settingMenu.add(new FlatMenuItem());
        this.add(settingMenu);

        FlatMenu about = new FlatMenu();
        about.setText("关于");
        this.add(about);
    }
}
