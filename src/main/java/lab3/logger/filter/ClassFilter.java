package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ClassFilter")
@XmlRootElement
public class ClassFilter extends Filter {
    public ClassFilter(String keyClass) {
        super(keyClass);
    }

    private String classNameToString(String className) {
        return className.substring(6, className.length());
    }

    @Override
    public boolean filter(Level level, Class clazz, String message) {
        if (classNameToString(clazz.toString()).compareTo(getKey()) == 0) {
            return false;
        }
        return true;
    }
}
