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
    private FileSelectMode fileSelectMode;
    private static final JFrame fileChooserFrame = new JFrame("选择文件");

    public FileChooser() {
        this.setFileSelectionMode(FILES_ONLY);
    }

    @Override
    public File getSelectedFile() {
        File file = super.getSelectedFile();
        if (file != null
                && this.fileSelectMode == FileSelectMode.SAVE_VIDEO
                && !file.getName().endsWith("." + this.getFileFilter().getDescription()))
        {
            file = new File(
                    file.getAbsolutePath() + "." + this.getFileFilter().getDescription()
            );
        }
        return file;
    }

    public int showOpenDialog(FileSelectMode mode) {
        this.setMode(mode);
        return super.showOpenDialog(fileChooserFrame);
    }

    public int showSaveDialog(FileSelectMode mode) {
        this.setMode(mode);
        return super.showSaveDialog(fileChooserFrame);
    }

    public void removeAllFileFilter() {
        for (FileFilter f : this.getChoosableFileFilters()) {
            this.removeChoosableFileFilter(f);
        }
    }

    public void setMode(FileSelectMode mode) {
        this.fileSelectMode = mode;
        switch (mode) {
            case OPEN_AUDIO: {
                this.removeAllFileFilter();
                this.setAcceptAllFileFilterUsed(true);
                this.setFileFilter(AUDIO_FILE_FILTER);
            }
            case OPEN_VIDEO: {
                this.removeAllFileFilter();
                this.setAcceptAllFileFilterUsed(true);
                this.setFileFilter(VIDEO_FILE_FILTER);
            }
            case SAVE_VIDEO: {
                this.removeAllFileFilter();
                this.setAcceptAllFileFilterUsed(false);
                this.addChoosableFileFilter(MKV_FILE_FILTER);
                this.addChoosableFileFilter(MP4_FILE_FILTER);
            }
        }
    }
}
