package lab3.logger.filter;

import lab3.logger.append.ConsolAppender;
import lab3.logger.append.FileAppender;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.StringWriter;

@XmlSeeAlso({
        MessageTextFilter.class,
        //ExceptionTextFilter.class,
        LevelFilter.class,
        ClassFilter.class,
        DefaultFilter.class
})
public abstract class Filter {
    private String key;

    public Filter(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @XmlElement(name = "Key")
    public void setKey(String key) {
        this.key = key;
    }

    public abstract boolean filter(Level level, Class clazz, String message);
}
