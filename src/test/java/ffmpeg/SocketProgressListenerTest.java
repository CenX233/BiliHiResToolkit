package ffmpeg;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class SocketProgressListenerTest {
    @Test
    public void testSocketProgressHandler() throws InterruptedException, IOException {
        SocketProgressListener listener = new SocketProgressListener(new ProgressHandler() {
            @Override
            protected void update(HashMap<String, String> progress) {
                System.out.println(progress.get("progress"));
            }
        });
        listener.start();
        Thread.sleep(1000000);
    }
}
