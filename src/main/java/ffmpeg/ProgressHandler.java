package ffmpeg;

import java.util.HashMap;

public abstract class ProgressHandler {
    public ProgressHandler() {}
    protected void update(HashMap<String, String> progress) {}
}
