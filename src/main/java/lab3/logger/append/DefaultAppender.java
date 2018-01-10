package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

public class DefaultAppender extends Appender {

    public DefaultAppender(Layout layout) {
        super(layout);
    }

    public void log(Level level, Class clazz, String message) {
        System.out.println("DefaultAppender.log() " + message);
    }
}
