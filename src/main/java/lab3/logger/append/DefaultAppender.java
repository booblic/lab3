package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

public class DefaultAppender extends Appender {

    public static final String nameAppender = "lab3.logger.append.DefaultAppender";

    public DefaultAppender(Layout layout) {
        super(nameAppender, layout);
    }

    public DefaultAppender() {}

    public void log(Level level, Class clazz, String message) {
        System.out.println(layout.messageBuilder(level, clazz, message));
    }
}
