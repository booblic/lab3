package lab3.logger.append;

import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import java.io.*;

public class FileAppender extends Appender {

    public static final String nameAppender = "lab3.logger.append.FileAppender";

    public FileAppender(Layout layout) {
        super(nameAppender, layout);
    }

    public FileAppender() {}

    public void log(Level level, Class clazz, String message) {

        /*try(FileWriter fileWriter = new FileWriter(this.fileName)) {

            fileWriter.write(layout.messageBuilder(level, clazz, message));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
