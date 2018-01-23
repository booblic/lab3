package lab3.logger.categor;

import javax.xml.bind.annotation.XmlElement;

public class Category {

    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(){}

    @XmlElement(name = "CategoryName")
    public void setCategoryName(String categoryName) {
       this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}


