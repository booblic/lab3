package lab3.logger.append;

import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс представляющий консольный аппендер
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "ConsolAppender")
@XmlRootElement
public class ConsolAppender extends Appender {

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public ConsolAppender() {}

    /**
     * Конструктор консольного аппендера
     * @param layout - шаблон лога аппендера
     * @param filter - массив фильтров аппендера
     */
    public ConsolAppender(Layout layout, Filter... filter) {
        super(layout, filter);
    }

    /**
     *  Метод, служащий для вывода логов на консоль
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exception - объект исключения
     */
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
