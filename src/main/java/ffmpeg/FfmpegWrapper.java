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
    private PipedOutputStream os = new PipedOutputStream();
    private final PipedInputStream is = new PipedInputStream();
    private static File ffmpegPath = new File(".\\ffmpeg\\");
    private final SocketProgressListener listener = new SocketProgressListener();

    /**
     * 初始化socket进度监听服务器.
     */
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

        String url = listener.getUrl();
        if (url != null) {
            cmdLine.addArgument("-progress");
            cmdLine.addArgument(listener.getUrl());
        }
    }

    @NotNull
    private FixedPumpStreamHandler getFixedPumpStreamHandler() throws IOException {
        os.connect(this.is);
        return new FixedPumpStreamHandler(
                /* output */
                new LogOutputStream() {
                    @Override
                    protected void processLine(String line, int logLevel) {
                        log.info(line.replaceAll("\0", ""));
                    }
                },
                /* err */
                new LogOutputStream() {
                    @Override
                    protected void processLine(String line, int logLevel) {
                        log.info(line.replaceAll("\0", ""));
                    }
                },
                /* input */
                this.is
        );
    }

    /**
     * 设置进度监听器.
     * @param handler 监听器
     */
    public void setProgressHandler(ProgressHandler handler) {
        listener.setHandler(handler);
    }

    /**
     * 获取进度监听器.
     * @return 监听器
     */
    public ProgressHandler getProgressHandler() {
        return listener.getHandler();
    }

    /**
     * 获取当前设置的FFmpeg可执行文件路径.
     * @return 路径
     */
    public File getFfmpegPath() {
        return ffmpegPath;
    }

    /**
     * 设置FFmpeg可执行文件的路径.
     * @param ffmpegPath 路径
     */
    public void setFfmpegPath(File ffmpegPath) {
        FfmpegWrapper.ffmpegPath = ffmpegPath;
    }

    /**
     * 通过向FFmpeg进程发送'q'命令使其主动退出.<br>
     * 如果无法发送命令将强制结束进程.<br>
     * 通过此方法结束ffmpeg进程ExecuteResultHandler的回调将带去返回值-1.<br>
     * 如果在调用此方法前ffmpeg进程已停止将不会再次触发回调.
     */
    public void stop() {
        boolean killed = executor.getWatchdog().killedProcess();
        if (!killed) {
            try {
                os.write('q');
                os.flush();
                os.close();
//                resultHandler.onProcessComplete(-1);
            } catch (IOException e) {
                log.log(Level.SEVERE, "无法发送关闭命令,将强制结束ffmpeg进程.", e);
                executor.getWatchdog().destroyProcess();
//                resultHandler.onProcessFailed(new ExecuteException("killed", -1));
            }
        }
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
        executor.execute(cmdLine, resultHandler);
    }

    public void run() throws IOException {
        executor.execute(cmdLine);
    }
}
