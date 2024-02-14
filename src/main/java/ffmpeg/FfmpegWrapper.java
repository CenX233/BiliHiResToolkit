package ffmpeg;

import logging.LoggerBuilder;
import org.apache.commons.exec.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FfmpegWrapper {
    private static CommandLine cmdLine;
    private DefaultExecutor executor;
    private static final Logger log = LoggerBuilder.getLogger("FFmpeg");
    private PipedOutputStream os;
    private static File ffmpegPath = new File(".\\ffmpeg\\");
    private final SocketProgressListener listener = new SocketProgressListener();
    private ExecuteResultHandler resultHandler;

    public FfmpegWrapper() {
        try {
            this.os = new PipedOutputStream();
            FixedPumpStreamHandler psh = getFixedPumpStreamHandler();

            executor = DefaultExecutor.builder()
                    .setExecuteStreamHandler(psh).get();
            executor.getStreamHandler().setProcessInputStream(this.os);
            listener.start();
            synchronized (listener) {
                listener.wait();
            }
        } catch (InterruptedException | IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        executor.setWatchdog(ExecuteWatchdog.builder()
                .setTimeout(Duration.ofDays(Integer.MAX_VALUE)).get());
        executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());

        cmdLine = new CommandLine(ffmpegPath + "\\ffmpeg");
        cmdLine.addArgument("-progress");
        cmdLine.addArgument(listener.getUrl());
    }

    @NotNull
    private FixedPumpStreamHandler getFixedPumpStreamHandler() throws IOException {
        PipedInputStream is = new PipedInputStream(os);
        FixedPumpStreamHandler psh = new FixedPumpStreamHandler(
                /* output */
                new LogOutputStream() {
                    @Override
                    protected void processLine(String line, int logLevel) {
                        log.info(line);
                    }
                },
                /* err */
                new LogOutputStream() {
                    @Override
                    protected void processLine(String line, int logLevel) {
                        log.info(line);
                    }
                },
                is
        );
        return psh;
    }

    public void setProgressHandler(ProgressHandler handler) {
        listener.setHandler(handler);
    }

    public ProgressHandler getProgressHandler() {
        return listener.getHandler();
    }

    public File getFfmpegPath() {
        return ffmpegPath;
    }

    public void setFfmpegPath(File ffmpegPath) {
        FfmpegWrapper.ffmpegPath = ffmpegPath;
    }

    /**
     * 结束ffmpeg进程。
     */
    public void stop() {
        try {
            os.write('q');
            os.flush();
            resultHandler.onProcessComplete(-1);
        } catch (IOException e) {
            log.log(Level.SEVERE, "无法发送关闭命令,将强制结束ffmpeg进程.", e);
            executor.getWatchdog().destroyProcess();
        }
//        executor.getWatchdog().destroyProcess();
//        listener.destroy();
    }

    /**
     * 添加参数
     * @param arg 参数
     */
    public void addArgument(String arg) {
        cmdLine.addArgument(arg);
    }

    /**
     * 运行ffmpeg
     * @param resultHandler 运行结果处理器
     * @throws IOException IO异常
     */
    public void run(ExecuteResultHandler resultHandler) throws IOException {
        log.info("Run ffmpeg with args:" + cmdLine);
        this.resultHandler = resultHandler;
        executor.execute(cmdLine, resultHandler);
//        try {
//            executor.getStreamHandler().setProcessInputStream(ffmpegInput);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void run() throws IOException {
        executor.execute(cmdLine);
    }
}
