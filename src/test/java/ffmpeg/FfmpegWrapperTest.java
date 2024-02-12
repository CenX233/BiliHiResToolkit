package ffmpeg;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FfmpegWrapperTest {
    @Test
    public void testFfmpegWrapper() throws IOException, InterruptedException {
        final boolean[] result = {true};
        ExecuteResultHandler handler = new ExecuteResultHandler() {
            @Override
            public void onProcessComplete(int exitValue) {
                System.err.println("success");
            }

            @Override
            public void onProcessFailed(ExecuteException e) {
                System.err.println("failed");
                result[0] = false;
            }
        };

        FfmpegWrapper ffmpeg = new FfmpegWrapper();
        ffmpeg.addArgument("-version");
        ffmpeg.run(handler);
        Thread.sleep(233);

        ffmpeg = new FfmpegWrapper(".\\ffmpeg\\");
        ffmpeg.addArgument("-codecs");
        ffmpeg.addArgument("-hide_banner");
        ffmpeg.run(handler);
        Thread.sleep(233);

        assertTrue(result[0]);
    }
}
