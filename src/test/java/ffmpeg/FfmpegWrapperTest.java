package ffmpeg;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class FfmpegWrapperTest {
    @Test
    public void testFfmpegWrapper() throws IOException, InterruptedException {
        System.out.println("test");
        final boolean[] result = {true};
        ExecuteResultHandler handler = new ExecuteResultHandler() {
            @Override
            public void onProcessComplete(int exitValue) {
                System.err.println("success with:" + exitValue);
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
        Thread.sleep(233);

        ffmpeg = new FfmpegWrapper();
        ffmpeg.setProgressHandler(progressHandler);

        ffmpeg.addArgument("-y");
        ffmpeg.addArgument("-f");
        ffmpeg.addArgument("gdigrab");
        ffmpeg.addArgument("-framerate");
        ffmpeg.addArgument("60");
        ffmpeg.addArgument("-i");
        ffmpeg.addArgument("desktop");
        ffmpeg.addArgument("output.mp4");
        ffmpeg.run(handler);
        Thread.sleep(5000);
        ffmpeg.stop();
        Thread.sleep(1000);

        assertTrue(result[0]);
    }
}
