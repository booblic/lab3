package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ThreadFilter")
@XmlRootElement
public class ThreadFilter extends Filter {

    private String keyThreadName;

    public ThreadFilter(String keyThreadName) {
        this.keyThreadName = keyThreadName;
    }

    public ThreadFilter() {}

    public String getKeyThreadName() {
        return keyThreadName;
    }

    @XmlElement(name = "KeyThread")
    public void setKeyThreadName(String keyThreadName) {
        this.keyThreadName = keyThreadName;
    }

    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption) {

        if (threadName.compareTo(keyThreadName) == 0) {
            return false;
        }
        return true;
    }
}
