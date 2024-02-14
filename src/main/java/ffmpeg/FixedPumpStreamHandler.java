package ffmpeg;

import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.StreamPumper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FixedPumpStreamHandler extends PumpStreamHandler {
    public FixedPumpStreamHandler(OutputStream outputStream, OutputStream errorOutputStream, InputStream inputStream) {
        super(outputStream, errorOutputStream, inputStream);
    }

    @Override
    protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
        os = new AutoFlushingOutputStream(os);
        Thread t =  new Thread(
                new StreamPumper(is, os, closeWhenExhausted),
                "stream pump"
        );
        t.setDaemon(true);
        return t;
    }
}

class AutoFlushingOutputStream extends OutputStream {
    private final OutputStream os;

    AutoFlushingOutputStream(OutputStream os) {
        this.os = os;
    }

    @Override
    public void write(int b) throws IOException {
        this.os.write(b);
        this.flush();
    }

    @Override
    public void write(byte @NotNull [] b) throws IOException {
        this.os.write(b);
        this.flush();
    }

    @Override
    public void write(byte @NotNull [] b, int off, int len) throws IOException {
        this.os.write(b, off, len);
        this.flush();
    }

    @Override
    public void flush() throws IOException {
        this.os.flush();
    }

    @Override
    public void close() throws IOException {
        this.os.close();
    }
}
