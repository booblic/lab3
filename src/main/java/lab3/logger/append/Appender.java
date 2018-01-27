package lab3.logger.append;

import lab3.logger.filter.DefaultFilter;
import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Абстрактный класс, точка иерархии всех аппендеров
 * @author Кирилл
 * @version 1.0
 */
@XmlSeeAlso({
        ConsolAppender.class,
        FileAppender.class,
        DataBaseAppender.class
})
public abstract class Appender {

    /**
     * Шаблон лога аппендера
     */
    private Layout layout;

    /**
     * Массив фильтров аппендера
     */
    private Filter[] filter;

    /**
     * Конструктор аппедера
     * @param layout - шаблон лога аппендера
     * @param filter - массив фильтров аппендера
     */
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

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public Appender() {}

    /**
     * Сеттер шаблона аппендера
     * @param layout - шаблон лога аппендера
     */
    @XmlElement(name = "Layouts")
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * Геттер шаблона аппендера
     * @return layout - шаблон лога аппендера
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Геттер массива фильтров аппендера
     * @return filter - массив фильтров аппендера
     */
    public Filter[] getFilter() {
        return filter;
    }

    /**
     * Сеттер массива фильтров аппендера
     * @param filter - массив фильтров аппендера
     */
    @XmlElement(name = "Filter")
    public void setFilter(Filter... filter) {
        this.filter = filter;
    }

    /**
     * Метод для форматирования трассировки стека исключения в строку
     * @param exception - объект исключения
     * @return stackTrace - строка трассировки стека исключения
     */
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

    /**
     * Абстрактный метод, служащий для записи логов
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exception - объект исключения
     */
    public abstract void log(Level level, Class clazz, String threadName, String message, Throwable... exception);
}
