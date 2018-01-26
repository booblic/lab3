package lab3.logger.layout;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Layout {

    private String layouts;

    private String separator;

    public Layout(String layouts, String... separator) {
        this.layouts = layouts;
        if (separator.length == 0) {
            this.separator = "|";
        } else {
            this.separator = separator[0];
        }
    }

    public Layout() {}

    @XmlElement(name = "Layout")
    public void setLayouts(String layout) {
        this.layouts = layout;
    }

    public String getLayouts() {
        return layouts;
    }

    public String getSeparator() {
        return separator;
    }

    @XmlElement(name = "Separator")
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String messageBuilder(Level level, Class clazz, String threadName, String message, String... stackTrace) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String layout: layouts.split(" ")) {

            String formatDate = null;

            Pattern p = Pattern.compile("\\%d\\{");
            Matcher m = p.matcher(layout);

            if (m.find()) {
                formatDate = layout.substring(3, layout.length() - 1);
                layout = layout.substring(0, 2);
            } else {
                formatDate = "d.m.y--H:m:s";
            }

            switch (layout) {
                case "%d":
                    SimpleDateFormat date = new SimpleDateFormat(formatDate);
                    stringBuilder.append(separator + "Date: " + date.format(new Date()));
                    break;

                case "%p":
                    stringBuilder.append(separator + "Level: " + level.getLevelStr());
                    break;

                case "%c":
                    stringBuilder.append(separator + "Class: " + clazz.getName());
                    break;

                case "%m":
                    stringBuilder.append(separator + "Message: " + message);
                    break;
                case "%t":
                    stringBuilder.append(separator + "Thread name: " + threadName);
                    break;
                case "%s":
                    if (stackTrace[0] != null) {
                        stringBuilder.append("\n" + "Stack trace: " + stackTrace[0]);
                        break;
                    }
            }
        }
        return stringBuilder.toString().trim() + "\n";
    }
}
