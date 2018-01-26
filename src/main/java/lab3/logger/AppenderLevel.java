package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlType(propOrder = {"appender", "level"})
public class AppenderLevel implements Comparable<AppenderLevel> {
    private Level level;
    private Appender[] appenders;

    public AppenderLevel(Level level, Appender... appenders) {
        this.level = level;
        this.appenders = appenders;
    }

    public AppenderLevel() {}

    @XmlJavaTypeAdapter(LevelAdapter.class)
    @XmlElement(name = "Level")
    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public Appender[] getAppenders() {
        return appenders;
    }

    @XmlElement(name = "Appender")
    public void setAppenders(Appender... appenders) {
        this.appenders = appenders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppenderLevel that = (AppenderLevel) o;
        return Objects.equals(level.getLevelValue(), that.level.getLevelValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(level.getLevelValue());
    }

    @Override
    public int compareTo(AppenderLevel o) {
        return level.getLevelValue() - o.level.getLevelValue();
    }
}
