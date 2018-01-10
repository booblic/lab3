package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import java.util.Date;

public class ConsolAppender extends Appender {

    public ConsolAppender(Layout layout) {
        super(layout);
    }

    public void log(Level level, Class clazz, String message) {
        if (level.levelValue >= 5) {
            System.out.println(messageBuilder(level, clazz, message));
            return;
        } else if (level.levelValue >= 4) {
            System.out.println(messageBuilder(level, clazz, message));
            return;
        } else if (level.levelValue >= 3) {
            System.out.println(messageBuilder(level, clazz, message));
            return;
        } else if (level.levelValue >= 2) {
            System.out.println(messageBuilder(level, clazz, message));
            return;
        } else if (level.levelValue >= 1) {
            System.out.println(messageBuilder(level, clazz, message));
            return;
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
