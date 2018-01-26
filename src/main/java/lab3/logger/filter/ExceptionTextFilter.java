package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.PrintWriter;
import java.io.StringWriter;

@XmlType(name = "ExceptionTextFilter")
@XmlRootElement
public class ExceptionTextFilter extends Filter {

    private String keyException;

    public ExceptionTextFilter(String keyException) {
        this.keyException = keyException;
    }

    public ExceptionTextFilter() {}

    public String getKeyException() {
        return keyException;
    }

    @XmlElement(name = "KeyException")
    public void setKeyException(String keyException) {
        this.keyException = keyException;
    }

    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption) {

        String nameException = null;

        if (exeption.length != 0) {
            Class c = exeption[0].getClass();
            nameException = c.getSimpleName();
        } else {
            nameException = "";
        }

        if (nameException.compareTo(keyException) == 0) {
            return false;
        }
        return true;
    }
}
