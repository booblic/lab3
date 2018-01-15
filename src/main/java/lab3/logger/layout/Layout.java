package lab3.logger.layout;

import lab3.logger.level.Level;

import java.util.Date;

public class Layout {

    public String layouts;

    public Layout(String layouts) {
        this.layouts = layouts;
    }

    public String messageBuilder(Level level, Class clazz, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String layout: layouts.split(" ")) {
            switch (layout) {
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
