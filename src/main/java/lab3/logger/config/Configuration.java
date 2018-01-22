package lab3.logger.config;

import com.sun.xml.internal.txw2.annotation.XmlElement;
import lab3.logger.LevelApender;
import lab3.logger.append.*;
import lab3.logger.append.ConsolAppender;
import lab3.logger.categor.Categories;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlType(propOrder = {"categories", "levelApenderList"})
public class Configuration {

    public Categories categories;

    public List<LevelApender> levelApenderList = new ArrayList<>();
    //@XmlTransient
    //public Map<Appender, Level> appenderLevel = new HashMap<>();

    public Configuration(String catecoriesName, List<LevelApender> levelApenderList) {
        categories = new Categories(catecoriesName);
        this.levelApenderList = levelApenderList;
    }

    /*public Configuration(String catecoriesName, Map<Appender, Level> appenderLevel) {
        categories = new Categories(catecoriesName);
        this.appenderLevel = appenderLevel;
    }*/

    public Configuration(){}

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
