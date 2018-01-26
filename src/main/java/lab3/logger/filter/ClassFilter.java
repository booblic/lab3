package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ClassFilter")
@XmlRootElement
public class ClassFilter extends Filter {

    private String keyClass;

    public ClassFilter(String keyClass) {
        this.keyClass = keyClass;
    }

    public ClassFilter() {}

    public String getKeyClass() {
        return keyClass;
    }

    @XmlElement(name = "KeyClass")
    public void setKeyClass(String keyClass) {
        this.keyClass = keyClass;
    }

    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption) {
        if (clazz.getName().compareTo(keyClass) == 0) {
            return false;
        }
        return true;
    }
}
