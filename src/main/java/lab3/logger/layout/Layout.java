package lab3.logger.layout;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        for (String l: layout.split(" ")) {

            String formatDate = null;

            Pattern p = Pattern.compile("\\%d\\{");
            Matcher m = p.matcher(l);

            if (m.find()) {
                formatDate = l.substring(3, l.length() - 1);
                l = l.substring(0, 2);
            } else {
                formatDate = "d.m.y-H:m:s";
            }

            switch (l) {
                case "%d":
                    SimpleDateFormat date = new SimpleDateFormat(formatDate);
                    stringBuilder.append(" | " + date.format(new Date()));
                    break;

                case "%p":
                    stringBuilder.append(" [" + level.getLevelStr() + "]");
                    break;

                case "%c":
                    stringBuilder.append(" | " + clazz);
                    break;

                case "%m":
                    stringBuilder.append(" | " + message);
                    break;
            }
        }
        return stringBuilder.toString().trim() + "\n";
    }
}
