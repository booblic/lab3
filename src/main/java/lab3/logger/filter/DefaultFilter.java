package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, представляющий фильтр по-умолчанию
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "DefaultFilter")
@XmlRootElement
public class DefaultFilter extends Filter {

    /**
     * Конструктор класса
     * @param defaultKey - пустая строка
     */
    public DefaultFilter(String defaultKey) {}

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public DefaultFilter() {}

    /**
     * Метод, служащий для фильтрации логов, пропускающий все
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption) {
        return true;
    }
}
