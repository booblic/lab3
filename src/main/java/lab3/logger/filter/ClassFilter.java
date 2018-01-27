package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, представляющий фильтор по имени класса
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "ClassFilter")
@XmlRootElement
public class ClassFilter extends Filter {

    /**
     * Имя катеогии по которой проходит фильтрация
     */
    private String keyClass;

    /**
     * Конструктор файлового аппендера
     * @param keyClass - имя катеогии
     */
    public ClassFilter(String keyClass) {
        this.keyClass = keyClass;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public ClassFilter() {}

    /**
     * Геттер имени катеогии по которой проходит фильтрация
     * @return keyClass - имя катеогии по которой проходит фильтрация
     */
    public String getKeyClass() {
        return keyClass;
    }

    /**
     * Сеттер имени катеогии по которой проходит фильтрация
     * @param keyClass - именя катеогии по которой проходит фильтрация
     */
    @XmlElement(name = "KeyClass")
    public void setKeyClass(String keyClass) {
        this.keyClass = keyClass;
    }

    /**
     * Метод, служащий для фильтрации логов по категориям
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption) {
        if (clazz.getName().compareTo(keyClass) == 0) {
            return false;
        }
        return true;
    }
}
