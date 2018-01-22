package lab3.logger.categor;

import javax.xml.bind.annotation.XmlType;

//@XmlType(propOrder = {"categoriesName"}, name = "categories")
public class Categories {

    private String categoriesName;

    public Categories(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public Categories(){}

    public void setCategoriesName(String categoriesName) {
       this.categoriesName = categoriesName;
    }

    public String getCategoriesName() {
        return new String(categoriesName);
    }
}


