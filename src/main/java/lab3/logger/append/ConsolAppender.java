package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement
public class ConsolAppender extends Appender {

    public static final String nameAppender = "lab3.logger.append.ConsolAppender";

    public ConsolAppender() {
    }

    public ConsolAppender(Layout layout) {
        super(nameAppender, layout);
    }

    //public ConsolAppender() {}

    public void log(Level level, Class clazz, String message) {
        System.out.println(layout.messageBuilder(level, clazz, message));
    }
}
