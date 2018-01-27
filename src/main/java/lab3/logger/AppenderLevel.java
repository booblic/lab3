package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

/**
 * Класс для сопоставления апендера и уровня логирования
 * @author Кирилл
 * @version 1.0
 */
@XmlType(propOrder = {"appenders", "level"})
public class AppenderLevel implements Comparable<AppenderLevel> {

    /**
     * Уровень логирования
     */
    private Level level;

    /**
     * Массив аппендеров, соответсвующих уровню логирования
     */
    private Appender[] appenders;

    /**
     * Конструктор класса AppenderLevel
     * @param level - уровень логирования
     * @param appenders - массив аппендеров, соответсвующих уровню логирования
     */
    public AppenderLevel(Level level, Appender... appenders) {
        this.level = level;
        this.appenders = appenders;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public AppenderLevel() {}

    /**
     * Сеттер уровеня логирования
     * @param level - уровень логирования
     */
    @XmlJavaTypeAdapter(LevelAdapter.class)
    @XmlElement(name = "Level")
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Геттер уровеня логирования
     * @return level - уровень логирования
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Геттер массива аппендеров, соответсвующих уровню логирования
     * @return appenders - массив аппендеров, соответсвующих уровню логирования
     */
    public Appender[] getAppenders() {
        return appenders;
    }

    /**
     * Сеттер массива аппендеров, соответсвующих уровню логирования
     * @param appenders - массив аппендеров, соответсвующих уровню логирования
     */
    @XmlElement(name = "Appender")
    public void setAppenders(Appender... appenders) {
        this.appenders = appenders;
    }

    /**
     * Метод для определения равенства объектов AppenderLevel
     * @param o - массив аппендеров, соответсвующих уровню логирования
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppenderLevel that = (AppenderLevel) o;
        return Objects.equals(level.getLevelValue(), that.level.getLevelValue());
    }

    /**
     * Метод для вычисление хэш-кода объектов AppenderLevel по числовому значению уровня логирования
     * @return хэш-код объекта AppenderLevel
     */
    @Override
    public int hashCode() {
        return Objects.hash(level.getLevelValue());
    }

    /**
     * Метод для сравенеия объектов AppenderLeve, сравнения происходит по числовому значению уровня логирования
     * @return число - результат сравнения
     */
    @Override
    public int compareTo(AppenderLevel o) {
        return level.getLevelValue() - o.level.getLevelValue();
    }
}
