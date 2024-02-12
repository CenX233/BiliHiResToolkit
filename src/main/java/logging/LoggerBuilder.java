package logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.StreamHandler;

/**
 * @author CenX
 */
public class LoggerBuilder {
    private static Level logLevel = Level.INFO;
    private static boolean logToConsole = true;
    private static String logFilePath;
    private static OutputStream consoleStream;
    public static java.util.logging.Logger getLogger(String name) {
        BetterFormatter formatter = new BetterFormatter();
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(name);
        logger.setUseParentHandlers(false);

        logger.setLevel(logLevel);

        if (logToConsole) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(formatter);
            logger.addHandler(consoleHandler);
        }

        if (!(logFilePath == null)) {
            String path = logFilePath + LocalDate.now() + ".log";
            File file = new File(logFilePath);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    System.err.println("无法创建日志文件目标目录: " + logFilePath);
                }
            } else {
                try {
                    FileHandler fileHandler = new FileHandler(path, true);
                    fileHandler.setFormatter(formatter);
                    logger.addHandler(fileHandler);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        if (consoleStream != null) {
            StreamHandler streamHandler = new StreamHandler(consoleStream, formatter);
            logger.addHandler(streamHandler);
        }

        return logger;
    }

    public static void setLogLevel(Level logLevel) {
        LoggerBuilder.logLevel = logLevel;
    }

    public static void setLogLevel(String logLevel) {
        LoggerBuilder.logLevel = Level.parse(logLevel);
    }

    public static void setLogToConsole(boolean logToConsole) {
        LoggerBuilder.logToConsole = logToConsole;
    }

    public static void setLogFilePath(String logFilePath) {
        LoggerBuilder.logFilePath = logFilePath;
    }
    public static void setConsoleStream(final OutputStream stream) {
        consoleStream = stream;
    }
}
