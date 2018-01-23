package lab3.logger.main;

import lab3.logger.AppenderLevel;
import lab3.logger.append.ConsolAppender;
import lab3.logger.categor.Category;
import lab3.logger.config.Config;
import lab3.logger.config.Configuration;
import lab3.logger.level.Level;

import javax.xml.bind.JAXB;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

/**
 * @author VYZH
 * @since 22.01.2018
 */
public class ConfigurationTest {
    /*public static void main(String[] args) {
        Config conf = new Config();
        Configuration configuration = new Configuration();
        conf.configurations.add(configuration);

        configuration.category = new Category();
        configuration.category.setCategoryName("a.b.c");

        AppenderLevel appenderLeve = new AppenderLevel(Level.TRACE, new ConsolAppender());
        configuration.appenderLevelList.add(appenderLeve);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JAXB.marshal(conf, out);

        System.out.println(out.toString());

        Config conf2 = JAXB.unmarshal(new ByteArrayInputStream(out.toByteArray()), Config.class);
        System.out.println("conf2 = " + conf2);
    }*/
}
