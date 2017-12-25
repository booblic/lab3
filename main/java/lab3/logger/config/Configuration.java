package lab3.logger.config;

import lab3.logger.append.*;
import lab3.logger.append.ConsolAppender;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;
import lab3.logger.main.Main;

import java.util.*;

public class Configuration {

    public static Map<Class, Map<List<Level>, List<Appender>>> mapMap = new HashMap<>();

    public static Map<Class, List<Appender>> map = new HashMap<>();

    static {
        Map<List<Level>, List<Appender>> m1 = new HashMap<>();
        m1.put(new ArrayList<>(Arrays.asList(Level.INFO, Level.DEBUG)), new ArrayList<>(Arrays.asList(new ConsolAppender(new Layout("%p %c %m %d")))));
        mapMap.put(Main.class, m1);
    }
}
