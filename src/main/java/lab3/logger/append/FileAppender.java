package lab3.logger.append;

import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.*;
import java.io.*;

/**
 * Класс представляющий файловый аппендер
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "FileAppender")
@XmlRootElement
public class FileAppender extends Appender {

    /**
     * Имя файла
     */
    private String fileName;

    /**
     * Ссылка для работы создания потока вывода в файл
     */
    File file;

    /**
     * Ссылка для работы создания потока вывода в файл
     */
    FileWriter fileWriter;

    /**
     * Ссылка на объект типа MyShutdownHook extends Thread, потока запускаемого при остановки программы
     */
    MyShutdownHook myShutdownHook;

    /**
     * Конструктор файлового аппендера
     * @param fileName - имя файла
     * @param layout - шаблон лога аппендера
     * @param filter - массив фильтров аппендера
     */
    public FileAppender(String fileName, Layout layout, Filter... filter) throws IOException {
        super(layout, filter);
        this.fileName = fileName;
        file = new File(fileName);
        fileWriter = new FileWriter(file, true);
        myShutdownHook = new MyShutdownHook();
        Runtime.getRuntime().addShutdownHook(myShutdownHook);
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public FileAppender() throws IOException {}

    /**
     * Сеттер имени файла
     * @param fileName - имя файла
     */
    @XmlAttribute(name = "FileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Геттер имени файла
     * @return fileName - имя файла
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Метод, служащий для записи логов в файл
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exception - объект исключения
     */
    public void log(Level level, Class clazz, String threadName, String message, Throwable... exception) {

        for (Filter f: getFilter()) {
            if (f.filter(level, clazz, threadName, message, exception) == false) {
                return;
            }
        }

        try {

            synchronized (FileAppender.class) {
                if (file == null || fileWriter == null) {
                    file = new File(fileName);
                    fileWriter = new FileWriter(file, true);
                    myShutdownHook = new MyShutdownHook();
                    Runtime.getRuntime().addShutdownHook(myShutdownHook);
                }
            }

            fileWriter.write(getLayout().messageBuilder(level, clazz, threadName, message, getPrintStacTrace(exception)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Внутренний класс поток-ловушка
     * @author Кирилл
     * @version 1.0
     */
    private class MyShutdownHook extends Thread {

        /**
         * Метод, run() в котором вызывается метод, закрывающий файл
         */
        public void run() {
            shutdown();
        }
    }

    /**
     * Метод, закрывающий файл
     */
    private void shutdown() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
