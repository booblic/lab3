package lab3.logger.config;

import lab3.logger.AppenderLevel;
import lab3.logger.categor.Category;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Класс для сопоставления категории и аппендеров с соответсвующими им уровнями логирования
 * @author Кирилл
 * @version 1.0
 */
@XmlType(propOrder = {"category", "appenderLevelList"})
public class Configuration {

    /**
     * Категория логирования
     */
    private Category category;

    /**
     * Коллекция аппендеров с соответсвующими им уровнями логирования
     */
    private List<AppenderLevel> appenderLevelList = new ArrayList<>();

    /**
     * Конструктор класса Configuration
     * @param catecoryName - категория логирования
     * @param appenderLevel - коллекция аппендеров с соответсвующими им уровнями логирования
     */
    public Configuration(String catecoryName, List<AppenderLevel> appenderLevel) {
        category = new Category(catecoryName);
        this.appenderLevelList = appenderLevel;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public Configuration() {}

    /**
     * Геттер категории логирования
     * @return category - категория логирования
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Сеттер категории логирования
     * @param category - категория логирования
     */
    @XmlElement(name = "Category")
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Геттер коллекции аппендеров с соответсвующими им уровнями логирования
     * @return appenderLevelList - коллекция аппендеров с соответсвующими им уровнями логирования
     */
    public List<AppenderLevel> getAppenderLevelList() {
        return appenderLevelList;
    }

    /**
     * Сеттер коллекции аппендеров с соответсвующими им уровнями логирования
     * @param appenderLevelList - коллекция аппендеров с соответсвующими им уровнями логирования
     */
    @XmlElement(name = "AppenderLevel")
    public void setAppenderLevelList(List<AppenderLevel> appenderLevelList) {
        this.appenderLevelList = appenderLevelList;
    }
}
