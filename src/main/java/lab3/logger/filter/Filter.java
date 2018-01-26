package lab3.logger.filter;

import lab3.logger.append.ConsolAppender;
import lab3.logger.append.FileAppender;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.StringWriter;

@XmlSeeAlso({
        MessageTextFilter.class,
        ExceptionTextFilter.class,
        LevelFilter.class,
        ClassFilter.class,
        DefaultFilter.class,
        ThreadFilter.class
})
public abstract class Filter {

    public Filter() {}

    public abstract boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption);
}
