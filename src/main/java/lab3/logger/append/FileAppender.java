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

    public FileAppender(String fileName, Layout layout, Filter... filter) {
        super(layout, filter);
        this.fileName = fileName;
    }

    public FileAppender() {}

    @XmlAttribute(name = "FileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void log(Level level, Class clazz, String message) {

        for (Filter f: getFilter()) {
            if (f.filter(level, clazz, message) == false) {
                setFilterFlag(false);
            }
        }

        if (getFilterFlag()) {
            try (FileWriter fileWriter = new FileWriter(this.fileName, true)) {

                fileWriter.write(getLayout().messageBuilder(level, clazz, message));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setFilterFlag(true);
    }
}
