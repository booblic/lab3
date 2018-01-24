package lab3.logger.append;

import lab3.logger.filter.DefaultFilter;
import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlSeeAlso({
        ConsolAppender.class,
        FileAppender.class,
        DataBaseAppender.class
})
public abstract class Appender {
    private Layout layout;

    private Filter[] filter;

    private boolean filterFlag = true;

    public Appender(Layout layout, Filter... filter) {
        this.layout = layout;
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

    public boolean getFilterFlag() {
        return filterFlag;
    }

    @XmlTransient
    public void setFilterFlag(boolean filterFlag) {
        this.filterFlag = filterFlag;
    }

    public abstract void log(Level level, Class clazz, String message);
}
