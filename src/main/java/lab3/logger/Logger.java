package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.config.Config;
import lab3.logger.config.Configuration;
import lab3.logger.config.SingletonConfig;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public class Logger{
    Class clazz;

    private TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

    private Logger(Class clazz, TreeSet<AppenderLevel> appenderLevels) {
        this.clazz = clazz;
        this.appenderLevels = appenderLevels;
    }

    public static Logger getLogger(Class clazz) {

        TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

        /*try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(new Config().readConfig(), new File("config.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

        String className = clazz.getName();

        try {
            for (Configuration configuration : SingletonConfig.getConfig().readConfig().getConfigurations()) {
                if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                    for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                        appenderLevels.add(levelApender);
                    }

                    return new Logger(clazz, appenderLevels);
                }

                String cn = className;

                while (className.contains(".")) {

                    className = className.substring(0, className.lastIndexOf("."));


                    if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                        for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                            appenderLevels.add(levelApender);
                        }

                        return new Logger(clazz, appenderLevels);
                    }
                }
                className = cn;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        appenderLevels.add(DefaultAppenderLevel.getDefaultAppenderLevel());
        return new Logger(clazz, appenderLevels);
    }

    public static Logger getLogger(Class clazz, String fileConfigName) {

        TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

        /*try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(SingletonConfig.getConfig().readConfig(), new File("config.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Config configUnmarsh = null;

        try {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            configUnmarsh = (Config) unmarshaller.unmarshal(new File(fileConfigName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/

        String className = clazz.getName();

        for (Configuration configuration : SingletonConfig.getConfig(fileConfigName).getConfigurations()) {
            if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {
                for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                    appenderLevels.add(levelApender);
                }

                return new Logger(clazz, appenderLevels);
            }

            String cn = className;

            while (className.contains(".")) {

                className = className.substring(0, className.lastIndexOf("."));


                if (configuration.getCategory().getCategoryName().compareTo(className) == 0) {

                    for (AppenderLevel levelApender: configuration.getAppenderLevelList()) {
                        appenderLevels.add(levelApender);
                    }

                    return new Logger(clazz, appenderLevels);
                }
            }
            className = cn;
        }

        appenderLevels.add(DefaultAppenderLevel.getDefaultAppenderLevel());
        return new Logger(clazz, appenderLevels);
    }

    public void log(Level level, String message, Throwable... exception) {
        SortedSet<AppenderLevel> la = appenderLevels.tailSet(new AppenderLevel(level, null), true);

        String threadName = Thread.currentThread().getName();

        for (AppenderLevel apenderLevel : la) {
            for (Appender appender: apenderLevel.getAppenders()) {
                appender.log(level, clazz, threadName, message, exception);
            }
        }
    }
}
