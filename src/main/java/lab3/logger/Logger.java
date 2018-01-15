package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.config.Configuration;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import java.util.*;

public class Logger{
    public List<Appender> appenders;
    public List<Level> levels;
    Class clazz;

    private static TreeSet<LevelApender> levelApenders = new TreeSet<>();

    private Logger(List<Level> levels, List<Appender> appenders, Class clazz) {
        this.levels = levels;
        this.appenders = appenders;
        this.clazz = clazz;
    }

    private static String classNameToString(String className) {
        return className.substring(6, className.length());
    }

    public static Logger getLogger(Class clazz) {

        String className = classNameToString(clazz.toString());

        for (Configuration configuration : Configuration.configurations) {
            if (configuration.categories.getCategoriesName().compareTo(className) == 0) {

                List<Level> levelList = new ArrayList<>();
                List<Appender> appenderList = new ArrayList<>();

                for (Map.Entry<Appender, Level> param : configuration.appenderLevel.entrySet()) {
                    levelApenders.add(new LevelApender(param.getValue(), param.getKey()));
                    //appenderList.add(param.getKey());
                    //levelList.add(param.getValue());
                }
                return new Logger(levelList, appenderList, clazz);
            }

            while (className.contains(".")) {
                className = className.substring(0, className.lastIndexOf("."));

                if (configuration.categories.getCategoriesName() == className) {

                    List<Level> levelList = null;
                    List<Appender> appenderList = null;

                    for (Map.Entry<Appender, Level> param : configuration.appenderLevel.entrySet()) {
                        levelApenders.add(new LevelApender(param.getValue(), param.getKey()));
                        //appenderList.add(param.getKey());
                        //levelList.add(param.getValue());
                    }
                    return new Logger(levelList, appenderList, clazz);
                }
            }
        }

        return new Logger(Arrays.asList(Level.INFO), Arrays.asList(new ConsolAppender(new Layout("defualt format"))), Object.class);
    }

        /*if (Configuration.mapMap.containsKey(className)) {
            Map<List<Level>, List<Appender>> map = Configuration.mapMap.get(className);

            List<Level> levelList = null;
            List<Appender> appenderList = null;

            for (Map.Entry<List<Level>, List<Appender>> param: map.entrySet()) {
               levelList = param.getKey();
               appenderList = param.getValue();
            }
            return new Logger(levelList, appenderList, clazz);
        }

        while (className.contains(".")) {
            className = className.substring(0, className.lastIndexOf("."));
            if (Configuration.mapMap.containsKey(className)) {
                Map<List<Level>, List<Appender>> map = Configuration.mapMap.get(className);

                List<Level> levelList = null;
                List<Appender> appenderList = null;

                for (Map.Entry<List<Level>, List<Appender>> param: map.entrySet()) {
                    levelList = param.getKey();
                    appenderList = param.getValue();
                }
                return new Logger(levelList, appenderList, clazz);
            }
        }

        return new Logger(Arrays.asList(Level.INFO), Arrays.asList(new ConsolAppender(new Layout("defualt format"))), Object.class);
    }*/

    public void log(Level level, String message) {

        SortedSet<LevelApender> la = levelApenders.tailSet(new LevelApender(level, null), true);
        for (LevelApender levelApender : la) {
            levelApender.getAppender().log(level, clazz, message);
        }

        /*for (Level l: levels) {
            for (int i = 0; i < appenders.size(); i++) {
                Appender a = appenders.get(i);
                if (level.levelValue <= l.levelValue) {
                    a.log(l, clazz, message);
                }
            }
        }
        /*for (Appender appender: appenders) {
            for (int i = 0; i < levels.size(); i++) {
                Level l = levels.get(i);
                if (level.levelValue <= l.levelValue) {
                    appender.log(level, clazz, message);
                }
            }
        }*/
    }

}
