package lab3.logger.layout;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

public class Layout {

    private String layout;

    public Layout(String layout) {
        this.layout = layout;
    }

    public Layout() {}

    @XmlElement(name = "Layout")
    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayout() {
        return layout;
    }

    public String messageBuilder(Level level, Class clazz, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String layout: layout.split(" ")) {
            switch (layout) {
                case "%d":
                    stringBuilder.append(" " + new Date().toString());
                    break;

                case "%p":
                    stringBuilder.append(" [" + level.getLevelStr() + "]");
                    break;

                case "%c":
                    stringBuilder.append(" " + clazz);
                    break;

                case "%m":
                    stringBuilder.append(" " + message);
                    break;
            }
        }
        return stringBuilder.toString().trim();
    }
}
