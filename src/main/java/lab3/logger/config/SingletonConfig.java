package lab3.logger.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class SingletonConfig {

    private static volatile Config codeConfig;

    private static volatile Config xmlConfig;

    private SingletonConfig() {}

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
