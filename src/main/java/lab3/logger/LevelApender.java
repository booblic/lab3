package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.append.ConsolAppender;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

/**
 * @author VYZH
 * @since 10.01.2018
 */
public class LevelApender {

    @XmlElement
    @XmlJavaTypeAdapter(LevelAdapter.class)
    public Level level = Level.TRACE;

    @XmlElementRef
    public Appender appender = new ConsolAppender(new Layout("%p %c %m %d"));


    public LevelApender(Level level, Appender appender) {
        this.level = level;
        this.appender = appender;
    }

    public Level getLevel() {
        return level;
    }

    public Appender getAppender() {
        return appender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelApender that = (LevelApender) o;
        return Objects.equals(level.levelValue, that.level.levelValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level.levelValue);
    }
}
