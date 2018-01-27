package lab3.logger.layout;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс представляющий шаблон лога
 * @author Кирилл
 * @version 1.0
 */
public class Layout {

    /**
     * Строка, описывающая шаблон лога
     */
    private String layouts;

    /**
     * Разделитель информации в логе
     */
    private String separator;

    /**
     * Конструктор шаблона лога
     * @param layouts - строка, описывающая шаблон лога
     * @param separator -разделитель информации в логе
     */
    public Layout(String layouts, String... separator) {
        this.layouts = layouts;
        if (separator.length == 0) {
            this.separator = "|";
        } else {
            this.separator = separator[0];
        }
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public Layout() {}

    /**
     * Сеттер строки, описывающий шаблон лога
     * @param layout - строка, описывающая шаблон лога
     */
    @XmlElement(name = "Layout")
    public void setLayouts(String layout) {
        this.layouts = layout;
    }

    /**
     * Геттер строки, описывающий шаблон лога
     * @return layouts - строка, описывающая шаблон лога
     */
    public String getLayouts() {
        return layouts;
    }

    /**
     * Геттер разделителя информации в логе
     * @return separator - разделитель информации в логе
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * Сеттер разделителя информации в логе
     * @param separator - разделитель информации в логе
     */
    @XmlElement(name = "Separator")
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * Метод, служащий для формирования строки лога в соответствии с заданным шаблоном
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param stackTrace - строка, представляющая трассировку стека исключения
     * @return строка лога, готовая для записи
     */
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
