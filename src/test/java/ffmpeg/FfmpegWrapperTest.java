package ffmpeg;

import static org.apache.commons.exec.util.DebugUtils.COMMONS_EXEC_DEBUG;
import static org.junit.jupiter.api.Assertions.*;

import logging.LoggerBuilder;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public class FfmpegWrapperTest {
    private static final Logger log = LoggerBuilder.getLogger("Test");
    @Test
    public void testFfmpegWrapper() throws IOException, InterruptedException {
//        System.setProperty(COMMONS_EXEC_DEBUG, "true");
        final boolean[] result = {true};
        ExecuteResultHandler handler = new ExecuteResultHandler() {
            @Override
            public void onProcessComplete(int exitValue) {
                log.info("success with:" + exitValue);
                System.out.println("success with:" + exitValue);
            }

            @Override
            public void onProcessFailed(ExecuteException e) {
                System.err.println("failed with:" + e.getExitValue());
                System.err.println(e.getMessage());
                e.printStackTrace();
                result[0] = false;
            }
        };

        ProgressHandler progressHandler = new ProgressHandler() {
            @Override
            protected void update(HashMap<String, String> progress) {
                System.out.println("frame count:" + progress.get("frame") + " | " + progress.get("progress"));
            }
        };

        FfmpegWrapper ffmpeg = new FfmpegWrapper();
        ffmpeg.setProgressHandler(progressHandler);

        ffmpeg.addArgument("-version");
        ffmpeg.run(handler);
        Thread.sleep(1000);
//        ffmpeg.stop();
//        Thread.sleep(100);

        ffmpeg = new FfmpegWrapper();
        ffmpeg.setProgressHandler(progressHandler);

        ffmpeg.addArgument("-hide_banner");
        ffmpeg.addArgument("-y");
        ffmpeg.addArgument("-t");
        ffmpeg.addArgument("3");
        ffmpeg.addArgument("-f");
        ffmpeg.addArgument("gdigrab");
        ffmpeg.addArgument("-framerate");
        ffmpeg.addArgument("60");
        ffmpeg.addArgument("-i");
        ffmpeg.addArgument("desktop");
        ffmpeg.addArgument("output.mp4");
        ffmpeg.run(handler);
        Thread.sleep(6000);
//        ffmpeg.stop();
//        Thread.sleep(6000);

        assertTrue(result[0]);
    }
}
