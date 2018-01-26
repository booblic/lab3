package lab3.logger.append;

import lab3.logger.filter.Filter;
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

    public ConsolAppender(Layout layout, Filter... filter) {
        super(layout, filter);
    }

    @Override
    public void log(Level level, Class clazz, String threadName, String message, Throwable... exception) {
        for (Filter f: getFilter()) {
            if (f.filter(level, clazz, threadName, message, exception) == false) {
                return;
            }
        }
        System.out.println(getLayout().messageBuilder(level, clazz, threadName, message, getPrintStacTrace(exception)));
    }
}
