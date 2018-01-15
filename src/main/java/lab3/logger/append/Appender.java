package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

public abstract class Appender {
    public Layout layout;

    public Appender(Layout layout) {
        this.layout = layout;
    }

    public abstract void log(Level level, Class clazz, String message);

}
