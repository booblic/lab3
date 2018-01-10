package lab3.logger.layout;

import lab3.logger.level.Level;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Layout {
    public List<String> layots = new ArrayList<>();

    public Layout(String format) {
        layots = splitter(format);
    }

    private List<String> splitter(String format) {
        List<String> list = new ArrayList<>();
        for (String str: format.split(" ")) {
            list.add(str);
        }
        return list;
    }

    public String messageBuilder(Level level, Class clazz, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str: layots) {
            switch (str) {
                case "%d":
                    stringBuilder.append(" " + new Date().toString());
                    break;

                case "%p":
                    stringBuilder.append(" [" + level.levelStr + "]");
                    break;

                case "%c":
                    stringBuilder.append(" " + clazz);
                    break;

                case "%m":
                    stringBuilder.append(" " + message);
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
