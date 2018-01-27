package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Абстрактный класс, точка иерархии всех фильтров
 * @author Кирилл
 * @version 1.0
 */
@XmlSeeAlso({
        MessageTextFilter.class,
        ExceptionTextFilter.class,
        LevelFilter.class,
        ClassFilter.class,
        DefaultFilter.class,
        ThreadFilter.class
})
public abstract class Filter {

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public Filter() {}

    /**
     * Абстрактный метод, служащий для фильтрации логов
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    public abstract boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption);
}
