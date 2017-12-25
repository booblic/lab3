package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

public abstract class Appender {
    Layout layout;

    public Appender(Layout layout) {
        this.layout = layout;
    }

    public abstract void log(Level level, Class clazz, String message);

    public abstract void trace(String message);

    public abstract void debug(String message);

    public abstract void info(String message);

    public abstract void warn(String message);

    public abstract void error(String message);
}
