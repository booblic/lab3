package lab3.logger;

import lab3.logger.append.ConsolAppender;
import lab3.logger.config.Config;
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

    private static TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

    private Logger(Class clazz) {
        this.clazz = clazz;
    }

    private static String classNameToString(String className) {
        return className.substring(6, className.length());
    }

    public static Logger getLogger(Class clazz) {

        /*try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(new Config().readCondif(), new File("config.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/

        /*Config configUnmarshl = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            configUnmarshl = (Config) unmarshaller.unmarshal(new File("config.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/

        String className = classNameToString(clazz.toString());

        for (Configuration configuration : new Config().readConfig().getConfigurations()) {
            if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                    appenderLevels.add(levelApender);
                }

                return new Logger(clazz);
            }

            String cn = className;

            while (className.contains(".")) {

                className = className.substring(0, className.lastIndexOf("."));


                if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                    for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                        appenderLevels.add(levelApender);
                    }

                    return new Logger(clazz);
                }
            }
            className = cn;
        }

        appenderLevels.add(new AppenderLevel());
        return new Logger(clazz);
    }

    public static Logger getLogger(Class clazz, String fileConfigName) {
        /*try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(new Config().readCondif(), new File("config.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/

        Config configUnmarshl = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            configUnmarshl = (Config) unmarshaller.unmarshal(new File(fileConfigName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        String className = classNameToString(clazz.toString());

        for (Configuration configuration : configUnmarshl.getConfigurations()) {
            if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                    appenderLevels.add(levelApender);
                }

                return new Logger(clazz);
            }

            String cn = className;

            while (className.contains(".")) {

                className = className.substring(0, className.lastIndexOf("."));


                if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                    for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                        appenderLevels.add(levelApender);
                    }

                    return new Logger(clazz);
                }
            }
            className = cn;
        }

        appenderLevels.add(new AppenderLevel());
        return new Logger(clazz);
    }

    public void log(Level level, String message) {
        SortedSet<AppenderLevel> la = appenderLevels.tailSet(new AppenderLevel(level, null), true);
        for (AppenderLevel apenderLevel : la) {
            apenderLevel.getAppender().log(level, clazz, message);
        }
    }

}
