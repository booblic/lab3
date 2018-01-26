package lab3.logger.append;

import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;

@XmlType(name = "FileAppender")
@XmlRootElement
public class FileAppender extends Appender {

    private String fileName;

    File file;
    FileWriter fileWriter;

    public FileAppender(String fileName, Layout layout, Filter... filter) throws IOException {
        super(layout, filter);
        file = new File(fileName);
        fileWriter = new FileWriter(file, true);
        MyShutdownHook myShutdownHook = new MyShutdownHook();
        Runtime.getRuntime().addShutdownHook(myShutdownHook);
    }

    public FileAppender() throws IOException {}

    @XmlAttribute(name = "FileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void log(Level level, Class clazz, String threadName, String message, Throwable... exception) {

        for (Filter f: getFilter()) {
            if (f.filter(level, clazz, threadName, message, exception) == false) {
                return;
            }
        }

        try {

            fileWriter.write(getLayout().messageBuilder(level, clazz, threadName, message, getPrintStacTrace(exception)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyShutdownHook extends Thread {
        public void run() {
            shutdown();
        }
    }

    private void shutdown() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
