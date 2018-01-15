package lab3.logger.categor;

public class Categories {

    private String categoriesName;

    public Categories(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
       this.categoriesName = categoriesName;
    }

    public String getCategoriesName() {
        return new String(categoriesName);
    }
}


