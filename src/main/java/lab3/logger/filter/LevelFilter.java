package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringWriter;

@XmlType(name = "LevelFilter")
@XmlRootElement
public class LevelFilter extends Filter {

    private String keyLevel;

    public LevelFilter(String keyLevel) {
        this.keyLevel = keyLevel;
    }

    public LevelFilter() {}

    public String getKeyLevel() {
        return keyLevel;
    }

    @XmlElement(name = "KeyLevel")
    public void setKeyLevel(String keyLevel) {
        this.keyLevel = keyLevel;
    }

    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption) {
        if (level.getLevelStr().compareTo(keyLevel) == 0) {
            return false;
        }
        return true;
    }
}
