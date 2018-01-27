package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, представляющий фильтор по сообщению
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "MessageTextFilter")
@XmlRootElement
public class MessageTextFilter extends Filter {

    /**
     * Ключевая строка по которой проходит фильтрация
     */
    private String keyWord;

    /**
     * Флаг, служащий для выбора типа фильтарции по ключевой строки (содержит/не содержит) по-умолчанию false - содержит
     */
    private boolean invert;

    /**
     * Конструктор филтра по уровням логировния
     * @param keyWord - уровень логирования
     * @param invert - флаг типа фильтрации (содержит/не содержит)
     */
    public MessageTextFilter(String keyWord, boolean... invert) {
        this.keyWord = keyWord;
        if (invert.length != 0) {
            this.invert = invert[0];
        } else {
            this.invert = false;
        }
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public MessageTextFilter() {}

    /**
     * Геттер ключевой строки по которой проходит фильтрация
     * @return keyWord - ключевая строка по которой проходит фильтрация
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * Сеттер ключевой строки по которой проходит фильтрация
     * @param keyWord - ключевая строка по которой проходит фильтрация
     */
    @XmlElement(name = "KeyWord")
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Геттер флага
     * @return invert - флаг, служащий для выбора типа фильтарции по ключевой строки (содержит/не содержит)
     */
    public boolean getInvert() {
        return invert;
    }

    /**
     * Сеттер флага
     * @param invert - флаг, служащий для выбора типа фильтарции по ключевой строки (содержит/не содержит)
     */
    @XmlAttribute(name = "Invert")
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    /**
     * Метод, служащий для фильтрации логов по сообщению
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exсeption - объект исключения
     * @return true/false - пропускает/не пропускает
     */
    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exсeption) {
        if (message.indexOf(keyWord) == 0) {
            return false;
        }
        return true;
    }
}
