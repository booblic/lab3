package lab3.logger.config;

import lab3.logger.append.*;
import lab3.logger.append.ConsolAppender;
import lab3.logger.categor.Categories;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import java.util.*;

public class Configuration {

    public Categories categories;

    public Map<Appender, Level> appenderLevel = new HashMap<>();

    public static List<Configuration> configurations = new ArrayList<>();

    static  {
        String category = "lab3.logger.main.Main";

        Map<Appender, Level> appenderLevelMap = new HashMap<>();

        appenderLevelMap.put(new ConsolAppender(new Layout("%p %c %m %d")), Level.WARN);

        Configuration configuration = new Configuration(category, appenderLevelMap);

        configurations.add(configuration);

    }

    private Configuration(String catecoriesName, Map<Appender, Level> appenderLevel) {
        categories = new Categories(catecoriesName);
        this.appenderLevel = appenderLevel;
    }

    //public static Map<String, Map<List<Level>, List<Appender>>> mapMap = new HashMap<>();

    /*static {
        Map<List<Level>, List<Appender>> m1 = new HashMap<>();
        m1.put(new ArrayList<>(Arrays.asList(Level.WARN)), new ArrayList<>(Arrays.asList(new ConsolAppender(new Layout("%p %c %m %d")))));
        mapMap.put("lab3.logger.main.Main", m1);
    }

    static {
        Map<List<Level>, List<Appender>> m2 = new HashMap<>();
        m2.put(new ArrayList<>(Arrays.asList(Level.INFO)), new ArrayList<>(Arrays.asList(new ConsolAppender(new Layout("%p %c %m %d")))));
        mapMap.put("lab3.logger", m2);
    }*/
}
