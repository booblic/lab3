package lab3.logger.config;

import lab3.logger.AppenderLevel;
import lab3.logger.categor.Category;
import javax.xml.bind.annotation.*;
import java.util.*;

@XmlType(propOrder = {"category", "appenderLevelList"})
public class Configuration {

    private Category category;

    private List<AppenderLevel> appenderLevelList = new ArrayList<>();

    public Configuration(String catecoryName, List<AppenderLevel> appenderLevel) {
        category = new Category(catecoryName);
        this.appenderLevelList = appenderLevel;
    }

    public Configuration() {}

    public Category getCategory() {
        return category;
    }

    @XmlElement(name = "Category")
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<AppenderLevel> getAppenderLevelList() {
        return appenderLevelList;
    }

    @XmlElement(name = "AppenderLevel")
    public void setAppenderLevelList(List<AppenderLevel> appenderLevelList) {
        this.appenderLevelList = appenderLevelList;
    }
}
