package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, представляющий фильтор по уровням логировния
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "LevelFilter")
@XmlRootElement
public class LevelFilter extends Filter {

    /**
     * Уровень логирования по которому проходит фильтрация
     */
    private String keyLevel;

    /**
     * Конструктор филтра по уровням логировния
     * @param keyLevel - уровень логирования
     */
    public LevelFilter(String keyLevel) {
        this.keyLevel = keyLevel;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public LevelFilter() {}

    /**
     * Геттер уровеня логирования по которому проходит фильтрация
     * @return keyLevel - уровень логирования по которой проходит фильтрация
     */
    public String getKeyLevel() {
        return keyLevel;
    }

    /**
     * Сеттер уровеня логирования по которому проходит фильтрация
     * @param keyLevel - уровень логирования по которой проходит фильтрация
     */
    @XmlElement(name = "KeyLevel")
    public void setKeyLevel(String keyLevel) {
        this.keyLevel = keyLevel;
    }

    /**
     * Метод, служащий для фильтрации логов по уровеню логирования
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption) {
        if (level.getLevelStr().compareTo(keyLevel) == 0) {
            return false;
        }
        return true;
    }
}
