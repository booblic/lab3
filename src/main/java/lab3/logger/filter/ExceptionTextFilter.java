package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, представляющий фильтор по имени исключения
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "ExceptionTextFilter")
@XmlRootElement
public class ExceptionTextFilter extends Filter {

    /**
     * Имя исключения по которому проходит фильтрация
     */
    private String keyException;

    /**
     * Конструктор филтра по имени исключения
     * @param keyException - имя исключения
     */
    public ExceptionTextFilter(String keyException) {
        this.keyException = keyException;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public ExceptionTextFilter() {}

    /**
     * Геттер имени исключения по которому проходит фильтрация
     * @return keyException - имя катеогии по которой проходит фильтрация
     */
    public String getKeyException() {
        return keyException;
    }

    /**
     * Сеттер имени исключения по которому проходит фильтрация
     * @param keyException - имя катеогии по которой проходит фильтрация
     */
    @XmlElement(name = "KeyException")
    public void setKeyException(String keyException) {
        this.keyException = keyException;
    }

    /**
     * Метод, служащий для фильтрации логов по имени исключения
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption) {

        String nameException = null;

        if (exсeption.length != 0) {
            Class c = exсeption[0].getClass();
            nameException = c.getSimpleName();
        } else {
            nameException = "";
        }

        if (nameException.compareTo(keyException) == 0) {
            return false;
        }
        return true;
    }
}
