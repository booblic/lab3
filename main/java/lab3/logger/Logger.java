package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.config.Configuration;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Logger{
    public List<Appender> appenders;
    public List<Level> levels;
    Class clazz;

    private Logger(List<Level> levels, List<Appender> appenders, Class clazz) {
        this.levels = levels;
        this.appenders = appenders;
        this.clazz = clazz;
    }

    public static Logger getLogger(Class clazz) {
        if (Configuration.mapMap.containsKey(clazz)) {
            Map<List<Level>, List<Appender>> map = Configuration.mapMap.get(clazz);

            List<Level> levelList = null;
            List<Appender> appenderList = null;

            for (Map.Entry<List<Level>, List<Appender>> param: map.entrySet()) {
               levelList = param.getKey();
               appenderList = param.getValue();
            }

            return new Logger(levelList, appenderList, clazz);
        }
        return new Logger(Arrays.asList(Level.INFO), Arrays.asList(new ConsolAppender(new Layout("defualt format"))), Object.class);
    }

    public void log(Level level, String message) {
        for (Appender appender: appenders) {
            if (levels.contains(level)) {
                appender.log(level, clazz ,message);
            }
        }
    }

}
