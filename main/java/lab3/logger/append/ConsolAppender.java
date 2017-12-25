package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import java.util.Date;

public class ConsolAppender extends Appender {

    public ConsolAppender(Layout layout) {
        super(layout);
    }

    public void log(Level level, Class clazz, String message) {
        switch (level.levelStr) {
            case "TRACE":
                this.trace(messageBuilder(level, clazz, message));
                break;

            case "DEBUG":
                this.debug(messageBuilder(level, clazz, message));
                break;

            case "INFO":
                this.info(messageBuilder(level, clazz, message));
                break;

            case "WARN":
                this.warn(messageBuilder(level, clazz, message));
                break;

            case "ERROR":
                this.error(messageBuilder(level, clazz, message));
                break;
        }
    }

    private String messageBuilder(Level level, Class clazz, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str: layout.layots) {
            switch (str) {
                case "%d":
                    stringBuilder.append(" " + new Date().toString());
                    break;

                case "%p":
                    stringBuilder.append(" [" + level.levelStr + "]");
                    break;

                case "%c":
                    stringBuilder.append(" " + clazz);
                    break;

                case "%m":
                    stringBuilder.append(" " + message);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public void trace(String message) {
        System.out.println(message);
    }

    public void debug(String message) {
        System.out.println(message);
    }

    public void info(String message) {
        System.out.println(message);
    }

    public void warn(String message) {
        System.out.println(message);
    }

    public void error(String message) {
        System.out.println(message);
    }
}
