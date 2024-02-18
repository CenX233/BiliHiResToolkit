package ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser extends JFileChooser {
    private static final FileNameExtensionFilter VIDEO_FILE_FILTER = new FileNameExtensionFilter(
            "常见视频文件(avi,flv,mkv,mov,mp4)",
            "avi","flv","mkv","mov","mp4"
    );
    private static final FileNameExtensionFilter AUDIO_FILE_FILTER = new FileNameExtensionFilter(
            "常见无损音频文件(alac,ape,flac,dts,wav)",
            "alac","ape","flac","dts","wav"
    );
    private static final FileNameExtensionFilter MP4_FILE_FILTER = new FileNameExtensionFilter(
            "mp4",
            "mp4"
    );
    private static final FileNameExtensionFilter MKV_FILE_FILTER = new FileNameExtensionFilter(
            "mkv",
            "mkv"
    );
    private Mode mode;
    private static final JFrame fileChooserFrame = new JFrame("选择文件");

    public FileChooser() {
        this.setFileSelectionMode(FILES_ONLY);
//        this.addActionListener(e -> {
//            if (e.getActionCommand().equals(APPROVE_SELECTION)) {
//                this.getApproveButtonMnemonic();
//            }
//        });
    }

    @Override
    public File getSelectedFile() {
        File file = super.getSelectedFile();
        if (file != null
                && this.mode == Mode.SAVE_VIDEO
                && !file.getName().endsWith("." + this.getFileFilter().getDescription()))
        {
            file = new File(
                    file.getAbsolutePath() + "." + this.getFileFilter().getDescription()
            );
        }
        return file;
    }

    public int showOpenDialog(Mode mode) {
        this.setMode(mode);
        return super.showOpenDialog(fileChooserFrame);
    }

    public int showSaveDialog(Mode mode) {
        this.setMode(mode);
        return super.showSaveDialog(fileChooserFrame);
    }

    public void removeAllFileFilter() {
        for (FileFilter f : this.getChoosableFileFilters()) {
            this.removeChoosableFileFilter(f);
        }
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        switch (mode) {
            case OPEN_AUDIO -> {
                this.removeAllFileFilter();
                this.setAcceptAllFileFilterUsed(true);
                this.setFileFilter(AUDIO_FILE_FILTER);
            }
            case OPEN_VIDEO -> {
                this.removeAllFileFilter();
                this.setAcceptAllFileFilterUsed(true);
                this.setFileFilter(VIDEO_FILE_FILTER);
            }
            case SAVE_VIDEO -> {
                this.removeAllFileFilter();
                this.setAcceptAllFileFilterUsed(false);
                this.addChoosableFileFilter(MKV_FILE_FILTER);
                this.addChoosableFileFilter(MP4_FILE_FILTER);
            }
        }
    }
}

enum Mode {
    OPEN_AUDIO,
    OPEN_VIDEO,
    SAVE_VIDEO
}