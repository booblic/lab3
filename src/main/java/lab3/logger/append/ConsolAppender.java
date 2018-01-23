package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ConsolAppender")
@XmlRootElement
public class ConsolAppender extends Appender {

    public ConsolAppender() {}

    public ConsolAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void log(Level level, Class clazz, String message) {
        System.out.println(getLayout().messageBuilder(level, clazz, message));
    }
}
