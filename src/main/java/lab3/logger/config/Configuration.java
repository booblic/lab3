package lab3.logger.config;

import lab3.logger.append.*;
import lab3.logger.append.ConsolAppender;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;
import lab3.logger.main.Main;

import java.util.*;

public class Configuration {

    public static Map<String, Map<List<Level>, List<Appender>>> mapMap = new HashMap<>();

    static {
        Map<List<Level>, List<Appender>> m1 = new HashMap<>();
        m1.put(new ArrayList<>(Arrays.asList(Level.WARN)), new ArrayList<>(Arrays.asList(new ConsolAppender(new Layout("%p %c %m %d")))));
        mapMap.put(Main.class.toString(), m1);
    }

    static {
        Map<List<Level>, List<Appender>> m2 = new HashMap<>();
        m2.put(new ArrayList<>(Arrays.asList(Level.INFO)), new ArrayList<>(Arrays.asList(new ConsolAppender(new Layout("%p %c %m %d")))));
        mapMap.put("class lab3.logger", m2);
    }
}
