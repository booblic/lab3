package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringWriter;

@XmlType(name = "LevelFilter")
@XmlRootElement
public class LevelFilter extends Filter {
    public LevelFilter(String keyLevel) {
        super(keyLevel);
    }

    public LevelFilter() {}

    @Override
    public boolean filter(Level level, Class clazz, String message) {
        if (level.getLevelStr().compareTo(getKey()) == 0) {
            return false;
        }
        return true;
    }
}
