package lab3.logger.append;

import lab3.logger.filter.DefaultFilter;
import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.PrintWriter;
import java.io.StringWriter;

@XmlSeeAlso({
        ConsolAppender.class,
        FileAppender.class,
        DataBaseAppender.class
})
public abstract class Appender {
    private Layout layout;

    private Filter[] filter;

    public Appender(Layout layout, Filter... filter) {
        if (layout == null) {
            this.layout = new Layout("%p %d{H:m:s,Y.M.D} %c %m %t %s");
        } else {
            this.layout = layout;
        }
        if (filter.length != 0) {
            this.filter = filter;
        } else {
            Filter[] f = new Filter[1];
            f[0] = new DefaultFilter("");
            this.filter = f;
        }
    }

    public Appender() {}

    @XmlElement(name = "Layouts")
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }

    public Filter[] getFilter() {
        return filter;
    }

    @XmlElement(name = "Filter")
    public void setFilter(Filter... filter) {
        this.filter = filter;
    }

    protected String getPrintStacTrace(Throwable... exception) {

        StringWriter sw = null;
        String stackTrace = null;

        if (exception.length != 0) {

            sw = new StringWriter();
            PrintWriter pw =new PrintWriter(sw);
            exception[0].printStackTrace(pw);
            stackTrace = sw.toString();
        }

        return stackTrace;
    }

    public abstract void log(Level level, Class clazz, String threadName, String message, Throwable... exception);
}
