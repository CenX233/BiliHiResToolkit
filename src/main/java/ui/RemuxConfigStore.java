package ui;

import ui.basic.EncodeMode;

import java.io.File;

public class RemuxConfigStore {
    private static File videoFile = new File("");
    private static EncodeMode videoEncodeMode = EncodeMode.COPY;
    private static int videoOffset = 0;
    private static File audioFile = new File("");
    private static EncodeMode audioEncodeMode = EncodeMode.AUTO;
    private static int audioOffset = 0;
    private static File outputFile = new File("");

    public static File getVideoFile() {
        return videoFile;
    }

    public static void setVideoFile(File videoFile) {
        RemuxConfigStore.videoFile = videoFile;
    }

    public static EncodeMode getVideoEncodeMode() {
        return videoEncodeMode;
    }

    public static void setVideoEncodeMode(EncodeMode videoEncodeMode) {
        RemuxConfigStore.videoEncodeMode = videoEncodeMode;
    }

    public static int getVideoOffset() {
        return videoOffset;
    }

    public static void setVideoOffset(int videoOffset) {
        RemuxConfigStore.videoOffset = videoOffset;
    }

    public static File getAudioFile() {
        return audioFile;
    }

    public static void setAudioFile(File audioFile) {
        RemuxConfigStore.audioFile = audioFile;
    }

    public static EncodeMode getAudioEncodeMode() {
        return audioEncodeMode;
    }

    public static void setAudioEncodeMode(EncodeMode audioEncodeMode) {
        RemuxConfigStore.audioEncodeMode = audioEncodeMode;
    }

    public static int getAudioOffset() {
        return audioOffset;
    }

    public static void setAudioOffset(int audioOffset) {
        RemuxConfigStore.audioOffset = audioOffset;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static void setOutputFile(File outputFile) {
        RemuxConfigStore.outputFile = outputFile;
    }
}
