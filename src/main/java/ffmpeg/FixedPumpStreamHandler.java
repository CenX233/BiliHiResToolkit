package ffmpeg;

import logging.LoggerBuilder;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.StreamPumper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FixedPumpStreamHandler extends PumpStreamHandler {
    private KeepAlive ka;
    public FixedPumpStreamHandler(OutputStream outputStream, OutputStream errorOutputStream, InputStream inputStream) {
        super(outputStream, errorOutputStream, inputStream);
    }

    @Override
    protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
        this.ka = new KeepAlive(os);
        os = new AutoFlushingOutputStream(os);
        ka.start();
        final Thread t =  new Thread(
                new StreamPumper(is, os, closeWhenExhausted),
                "stream pump"
        );
        t.setDaemon(true);
        return t;
    }

    @Override
    public void stop() throws IOException {
        ka.stopKeepAlive();
        super.stop();
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
        this.os.flush();
    }

    @Override
    public void write(byte @NotNull [] b) throws IOException {
        this.os.write(b);
        this.os.flush();
    }

    @Override
    public void write(byte @NotNull [] b, int off, int len) throws IOException {
        this.os.write(b, off, len);
        this.os.flush();
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

class KeepAlive extends Thread {
    private static final Logger log = LoggerBuilder.getLogger("PipeKeepAlive");
    private final OutputStream pos;
    private boolean run = true;
    public KeepAlive(OutputStream pos) {
        this.pos = pos;
    }
    @Override
    public void run() {
        while (run) {
            try {
                this.pos.write('\0');
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                break;
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }

    public void stopKeepAlive() {
        this.run = false;
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "停止Pipe心跳保持时异常.", e);
        }
    }
}