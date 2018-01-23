package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlSeeAlso({
        ConsolAppender.class,
        FileAppender.class
})
public abstract class Appender {
    private Layout layout;

    public Appender(Layout layout) {
        this.layout = layout;
    }

    public Appender() {}

    @XmlElement(name = "Layouts")
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }

    public abstract void log(Level level, Class clazz, String message);
}
