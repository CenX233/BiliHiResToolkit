package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileChooserTest {
    @BeforeAll
    public static void setup() {
        FlatDarkLaf.setup();
    }

    @Test
    public void testFileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.showOpenDialog();
        chooser.showSaveDialog();
    }
}
