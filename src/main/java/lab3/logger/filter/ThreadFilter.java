package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, представляющий фильтор по потокам
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "ThreadFilter")
@XmlRootElement
public class ThreadFilter extends Filter {

    /**
     * Имя потока по которой проходит фильтрация
     */
    private String keyThreadName;

    /**
     * Конструктор филтра по потокам
     * @param keyThreadName - имя потока по которой проходит фильтрация
     */
    public ThreadFilter(String keyThreadName) {
        this.keyThreadName = keyThreadName;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public ThreadFilter() {}

    /**
     * Геттер имени потока по которой проходит фильтрация
     * @return keyThreadName - имя потока по которой проходит фильтрация
     */
    public String getKeyThreadName() {
        return keyThreadName;
    }

    /**
     * Сеттер имени потока по которой проходит фильтрация
     * @param keyThreadName - имя потока по которой проходит фильтрация
     */
    @XmlElement(name = "KeyThread")
    public void setKeyThreadName(String keyThreadName) {
        this.keyThreadName = keyThreadName;
    }

    /**
     * Метод, служащий для фильтрации логов по имени потока
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption) {

        if (threadName.compareTo(keyThreadName) == 0) {
            return false;
        }
        return true;
    }
}
