package lab3.logger;

import lab3.logger.append.ConsolAppender;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

/**
 * Синглтон для создания и хранения AppenderLevel по-умолчанию, исполюзуется если, ни один из логеров для запрашеваемой котеогии не указан в конфигураци
 * @author Кирилл
 * @version 1.0
 */
public class DefaultAppenderLevel {

    /**
     * Объект AppenderLevel по-умолчанию
     */
    private static volatile AppenderLevel defaultappenderLevel;

    /**
     * Конструктор по-умолчанию
     */
    private DefaultAppenderLevel() {}

    /**
     * Метод для получания объект AppenderLevel
     * @return объект AppenderLevel по-умолчанию
     */
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
