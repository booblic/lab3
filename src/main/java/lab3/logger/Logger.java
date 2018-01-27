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

/**
 * Класс, предстваляющий логер
 * @author Кирилл
 * @version 1.0
 */
public class Logger{

    /**
     * Класс для которого создается логер
     */
    Class clazz;

    /**
     * Коллекция аппендеров с соответсувующими им уровнями
     */
    private TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

    /**
     * Конструктор класса Logger
     * @param clazz - класс для которого создается логер
     * @param appenderLevels - коллекция аппендеров с соответсувующими им уровнями
     */
    private Logger(Class clazz, TreeSet<AppenderLevel> appenderLevels) {
        this.clazz = clazz;
        this.appenderLevels = appenderLevels;
    }

    /**
     * Статический метод для создания логера на основе конфигураций (созданных на основе Java кода)
     * @param clazz - класс для которого создается логер
     * @return готовый логер
     */
    public static Logger getLogger(Class clazz) {

        TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

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

    /**
     * Статический метод для создания логера на основе конфигураций (созданных на основе XML файла)
     * @param clazz - класс для которого создается логер
     * @return готовый логер
     */
    public static Logger getLogger(Class clazz, String fileConfigName) {

        TreeSet<AppenderLevel> appenderLevels = new TreeSet<>();

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

    /**
     * Метод вызываемый для логирования, в нем выбираются аппендеры данного логера, которым нужно отправить лог
     * @param level - уровень логирования
     * @param message - сообщение пользователя
     * @param exception - исключение
     */
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
