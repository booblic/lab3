package lab3.logger.config;

import lab3.logger.AppenderLevel;
import lab3.logger.append.ConsolAppender;
import lab3.logger.append.FileAppender;
import lab3.logger.filter.ClassFilter;
import lab3.logger.filter.LevelFilter;
import lab3.logger.filter.MessageTextFilter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Configurations")
public class Config {

    private List<Configuration> configurations = new ArrayList<>();

    public Config() {}

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    @XmlElement(name = "Configuration")
    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }


    public Config readConfig() {
        String category1 = "lab3.logger.main";

        List<AppenderLevel> levelApenderList1 = new ArrayList<>();

        AppenderLevel levApp1 = new AppenderLevel(Level.INFO, new ConsolAppender(new Layout("%p %d{H:m:s,Y.M.D} %c %m"), new LevelFilter("WARN"), new MessageTextFilter("message")));

        AppenderLevel levApp2 = new AppenderLevel(Level.DEBUG, new FileAppender("log.txt", new Layout("%p %d{H:m:s,Y.M.D} %c %m"), new LevelFilter("INFO"), new MessageTextFilter("message")));

        levelApenderList1.add(levApp1);
        levelApenderList1.add(levApp2);

        Configuration c1 = new Configuration(category1, levelApenderList1);

        configurations.add(c1);

        return this;
    }
}
