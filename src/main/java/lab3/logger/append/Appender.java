package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@XmlSeeAlso({
        ConsolAppender.class
})
public abstract class Appender {
    public String nameAppender;
    public Layout layout;

    @XmlTransient
    public Class cl;
    public String fileName;

    public Appender(String nameAppender, Layout layout) {
        this.nameAppender = nameAppender;
        this.layout = layout;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Appender getAppender() {
        Appender appender = null;
        try {
            cl = Class.forName(this.nameAppender);

            Constructor constructor = null;

            if (cl.toString().compareTo("class lab3.logger.append.FileAppender") == 0) {
                constructor = cl.getConstructor(Layout.class);
                appender = (Appender) constructor.newInstance(layout);
                return appender;
            }

            constructor = cl.getConstructor(Layout.class);

            appender = (Appender) constructor.newInstance(layout);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return appender;
    }

    public Appender() {}

    public abstract void log(Level level, Class clazz, String message);

}
