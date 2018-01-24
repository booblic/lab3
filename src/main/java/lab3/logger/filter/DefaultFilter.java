package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class DefaultFilter extends Filter {

    public DefaultFilter(String defaultKey) {
        super(defaultKey);
    }

    public DefaultFilter() {}

    @Override
    public boolean filter(Level level, Class clazz, String message) {
        return true;
    }
}
