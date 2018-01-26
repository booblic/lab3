package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

public class DefaultAppenderLevel {
    private static volatile AppenderLevel defaultappenderLevel;

    private DefaultAppenderLevel() {}

    public static AppenderLevel getDefaultAppenderLevel() {
        AppenderLevel localDefaultappenderLevel = defaultappenderLevel;
        if (localDefaultappenderLevel == null) {
            synchronized (AppenderLevel.class) {
                localDefaultappenderLevel = defaultappenderLevel;
                if (localDefaultappenderLevel == null) {
                    defaultappenderLevel = localDefaultappenderLevel = new AppenderLevel(Level.DEBUG, new ConsolAppender(new Layout("%p %d{H:m:s,Y.M.D} %c %m %t %s", "|")));
                }
            }
        }
        return localDefaultappenderLevel;
    }
}
