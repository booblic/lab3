package lab3.logger.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Синглтон для создания и хранения конфигураций логера созданных на основе Java кода и XML файла
 * @author Кирилл
 * @version 1.0
 */
public class SingletonConfig {

    /**
     * Конфигурации логера созданных на основе Java кода
     */
    private static volatile Config codeConfig;

    /**
     * Конфигурации логера созданных на основе XML файла
     */
    private static volatile Config xmlConfig;

    /**
     * Конструктор по-умолчанию
     */
    private SingletonConfig() {}

    /**
     * Метод для получания объекта конфигураций логера созданных на основе Java кода
     * @return конфигурации логера созданных на основе Java кода
     */
    public static Config getConfig() {
        Config localCodeConfig = codeConfig;
        if (localCodeConfig == null) {
            synchronized (Config.class) {
                localCodeConfig = codeConfig;
                if (localCodeConfig == null) {
                    codeConfig = localCodeConfig = new Config();
                }
            }
        }
        return localCodeConfig;
    }

    /**
     * Метод для получания объекта конфигураций логера созданных на основе XML файла
     * @param fileConfigName - путь к файлу с конфигурацией
     * @return конфигурации логера созданных на основе XML файла
     */
    public static Config getConfig(String fileConfigName) {
        Config localXmlConfig = xmlConfig;
        if (localXmlConfig == null) {
            synchronized (Config.class) {
                localXmlConfig = xmlConfig;
                if (localXmlConfig == null) {
                    try {
                        JAXBContext context = JAXBContext.newInstance(Config.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();

                        xmlConfig = localXmlConfig = (Config) unmarshaller.unmarshal(new File(fileConfigName));
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return localXmlConfig;
    }
}
