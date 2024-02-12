package logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author CenX
 * 日志格式化器
 * 模仿了常见的log4j样式
 */
public class BetterFormatter extends Formatter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(record.getMillis()), ZoneId.systemDefault())));
        sb.append("] [");
        sb.append(record.getLoggerName());
        sb.append("|");
        sb.append(record.getLevel().getLocalizedName());
        sb.append("]: ");
        sb.append(formatMessage(record));
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            sb.append(sw);
        } else {
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
