package lab3.logger.categor;

import javax.xml.bind.annotation.XmlElement;

/**
 * Класс представляющий категорию логгирования
 * @author Кирилл
 * @version 1.0
 */
public class Category {

    /**
     * Имя категории
     */
    private String categoryName;

    /**
     * Конструктор класса
     * @param categoryName - имя категории
     */
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public Category(){}

    /**
     * Сеттер имени категории
     * @param categoryName - имя категории
     */
    @XmlElement(name = "CategoryName")
    public void setCategoryName(String categoryName) {
       this.categoryName = categoryName;
    }

    /**
     * Геттер имени категории
     * @return categoryName - имя категории
     */
    public String getCategoryName() {
        return categoryName;
    }
}


