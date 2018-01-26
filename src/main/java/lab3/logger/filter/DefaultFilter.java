package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DefaultFilter")
@XmlRootElement
public class DefaultFilter extends Filter {

    public DefaultFilter(String defaultKey) {}

    public DefaultFilter() {}

    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption) {
        return true;
    }
}
