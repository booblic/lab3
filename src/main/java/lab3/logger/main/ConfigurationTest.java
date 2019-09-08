package lab3.logger.main;

import lab3.logger.LevelApender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.categor.Categories;
import lab3.logger.config.Conf;
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
    public static void main(String[] args) {
        Conf conf = new Conf();
        Configuration configuration = new Configuration();
        conf.configurations.add(configuration);

        configuration.categories = new Categories();
        configuration.categories.setCategoriesName("a.b.c");

        LevelApender levelApender = new LevelApender(Level.TRACE, new ConsolAppender());
        configuration.levelApenderList.add(levelApender);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JAXB.marshal(conf, out);

        System.out.println(out.toString());

        Conf conf2 = JAXB.unmarshal(new ByteArrayInputStream(out.toByteArray()), Conf.class);
        System.out.println("conf2 = " + conf2);
    }
}
