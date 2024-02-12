package ffmpeg;

import logging.LoggerBuilder;
import org.apache.commons.exec.*;

import java.io.IOException;
import java.util.logging.Logger;

public class FfmpegWrapper {
    private final CommandLine cmdLine;
    DefaultExecutor executor;
    private static final Logger log = LoggerBuilder.getLogger("FFmpeg");

    /**
     * 使用内置的ffmpeg可执行文件并输出日志
     */
    public FfmpegWrapper() {
        cmdLine = new CommandLine(".\\ffmpeg\\ffmpeg");
        executor = DefaultExecutor.builder()
                .setExecuteStreamHandler(
                        new PumpStreamHandler(
                                new LogOutputStream() {
                                    @Override
                                    protected void processLine(String line, int logLevel) {
                                        log.info(line);
                                    }
                                }
                        )
                )
                .get();
        executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
    }

    /**
     * 使用指定路径下的ffmpeg可执行文件并输出日志
     */
    public FfmpegWrapper(String ffmpegPath) {
        cmdLine = new CommandLine(ffmpegPath + "ffmpeg");
        executor = DefaultExecutor.builder()
                .setExecuteStreamHandler(
                        new PumpStreamHandler(
                                new LogOutputStream() {
                                    @Override
                                    protected void processLine(String line, int logLevel) {
                                        log.info(line);
                                    }
                                }
                        )
                )
                .get();
        executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
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
        executor.execute(cmdLine, resultHandler);
    }

    public void run() throws IOException {
        executor.execute(cmdLine);
    }
}
