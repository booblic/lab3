package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.append.DefaultAppender;
import lab3.logger.append.FileAppender;
import lab3.logger.config.Conf;
import lab3.logger.config.Configuration;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import java.util.*;

public class Logger{
    Class clazz;

    private static TreeSet<LevelApender> levelApenders = new TreeSet<>();

    private Logger(Class clazz) {
        this.clazz = clazz;
    }

    private static String classNameToString(String className) {
        return className.substring(6, className.length());
    }

    public static Logger getLogger(Class clazz) {

        String category1 = "lab3.logger.main.Main";

        List<LevelApender> levelApenderList1 = new ArrayList<>();

        LevelApender levApp1 = new LevelApender(Level.WARN, new ConsolAppender(new Layout("%p %c %m %d")));

        levelApenderList1.add(levApp1);

        Configuration c1 = new Configuration(category1, levelApenderList1);


        String category2 = "lab3.logger";

        List<LevelApender> levelApenderList2 = new ArrayList<>();

        LevelApender levApp2 = new LevelApender(Level.DEBUG, new ConsolAppender(new Layout("%p %d %m %c")));

        LevelApender levApp3 = new LevelApender(Level.TRACE, new ConsolAppender(new Layout("%p %d %m %c")));

        levelApenderList2.add(levApp2);
        levelApenderList2.add(levApp3);

        Configuration c2 = new Configuration(category2, levelApenderList2);


        Conf conf = new Conf();
        conf.configurations.add(c1);
        conf.configurations.add(c2);

        try {
            JAXBContext context = JAXBContext.newInstance(Conf.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(conf, new File("conf.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Conf config = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Conf.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            config = (Conf) unmarshaller.unmarshal(new File("conf.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        String className = classNameToString(clazz.toString());

        for (Configuration configuration : config.configurations) {
            if (configuration.categories.getCategoriesName().compareTo(className) == 0) {

                for (LevelApender levelApender: configuration.levelApenderList) {
                    levelApenders.add(levelApender);
                }


                /*for (Map.Entry<Appender, Level> param : c.appenderLevel.entrySet()) {
                    System.out.println(param.getValue().levelStr);
                    System.out.println(param.getKey().getClass());
                    levelApenders.add(new LevelApender(param.getValue(), param.getKey()));
                }*/
                return new Logger(clazz);
            }

            String cn = className;

            while (className.contains(".")) {

                className = className.substring(0, className.lastIndexOf("."));


                if (configuration.categories.getCategoriesName().compareTo(className) == 0) {

                    for (LevelApender levelApender: configuration.levelApenderList) {
                        levelApenders.add(levelApender);
                    }

                    /*for (Map.Entry<Appender, Level> param : c.appenderLevel.entrySet()) {
                        levelApenders.add(new LevelApender(param.getValue(), param.getKey()));
                        //appenderList.add(param.getKey());
                        //levelList.add(param.getValue());
                    }*/
                    return new Logger(clazz);
                }
            }
            className = cn;
        }

        /*for (Configuration configuration36 : Configuration.configurations) {
            if (configuration36.categories.getCategoriesName().compareTo(className) == 0) {

                List<Level> levelList = new ArrayList<>();
                List<Appender> appenderList = new ArrayList<>();

                for (Map.Entry<Appender, Level> param : configuration36.appenderLevel.entrySet()) {
                    levelApenders.add(new LevelApender(param.getValue(), param.getKey()));
                    //appenderList.add(param.getKey());
                    //levelList.add(param.getValue());
                }
                return new Logger(clazz);
            }

            while (className.contains(".")) {
                className = className.substring(0, className.lastIndexOf("."));

                if (configuration36.categories.getCategoriesName() == className) {

                    List<Level> levelList = null;
                    List<Appender> appenderList = null;

                    for (Map.Entry<Appender, Level> param : configuration36.appenderLevel.entrySet()) {
                        levelApenders.add(new LevelApender(param.getValue(), param.getKey()));
                        //appenderList.add(param.getKey());
                        //levelList.add(param.getValue());
                    }
                    return new Logger(clazz);
                }
            }
        }*/

        return new Logger(Object.class);
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
            levelApender.getAppender().getAppender().log(level, clazz, message);
        }
    }

}
