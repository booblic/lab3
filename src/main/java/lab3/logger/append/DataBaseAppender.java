package lab3.logger.append;

import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс представляющий аппендер базы данных
 * @author Кирилл
 * @version 1.0
 */
@XmlType(name = "DataBaseAppender")
@XmlRootElement
public class DataBaseAppender extends Appender {

    /**
     * URL для подключения к базе данных
     */
    private String url;

    /**
     * Имя пользователя для подключения к базе данных
     */
    private String userName;

    /**
     * Пароль для подключения к базе данных
     */
    private String password;

    /**
     * Драйвер базы данных
     */
    private String driver;

    /**
     * SQL запрос
     */
    private String sqlRequest;

    /**
     * Конструктор консольного аппендера
     * @param url - URL для подключения к базе данных
     * @param userName - имя пользователя для подключения к базе данных
     * @param password - пароль для подключения к базе данных
     * @param driver - драйвер базы данных
     * @param sqlRequest - SQL запрос
     * @param layout - шаблон лога аппендера
     * @param filter - массив фильтров аппендера
     */
    public DataBaseAppender(String url, String userName, String password, String driver, String sqlRequest, Layout layout, Filter... filter) {
        super(layout, filter);
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driver = driver;
        this.sqlRequest = sqlRequest;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public DataBaseAppender() {}

    /**
     * Геттер URL для подключения к базе данных
     * @return url - URL для подключения к базе данных
     */
    public String getUrl() {
        return url;
    }

    /**
     * Сеттер URL
     * @param url - URL для подключения к базе данных
     */
    @XmlElement(name = "URL")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Геттер имени пользователя
     * @return userName - имя пользователя
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Сеттер имени пользователя
     * @param userName - имя пользователя для подключения к базе данных
     */
    @XmlElement(name = "UserName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Геттер пароля
     * @return password - пароль для подключения к базе данных
     */
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер пароля
     * @param password - пароль для подключения к базе данных
     */
    @XmlElement(name = "Password")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Геттер драйвера базы данных
     * @return driver - драйвер базы данных
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Сеттер драйвера базы данных
     * @param driver - драйвер базы данных
     */
    @XmlAttribute(name = "Driver")
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * Геттер SQL запроса
     * @return sqlRequest - SQL запрос
     */
    public String getSqlRequest() {
        return sqlRequest;
    }

    /**
     * Сеттер SQL запроса
     * @param sqlRequest - SQL запрос
     */
    @XmlElement(name = "SQLRequest")
    public void setSqlRequest(String sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    /**
     * Метод, служащий для записи логов в базу данных
     * @param level - уровень логирования
     * @param clazz - класс в котором создаются логи
     * @param threadName - имя потока
     * @param message - сообщение пользователя
     * @param exception - объект исключения
     */
    @Override
    public void log(Level level, Class clazz, String threadName, String message, Throwable... exception) {
        for (Filter f: getFilter()) {
            if (f.filter(level, clazz, threadName, message, exception) == false) {
                return;
            }
        }
        try {
            Class.forName(driver);
            try(Connection connection = DriverManager.getConnection(url, userName, password)) {

                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);

                int i = 1;

                for (String layout: getLayout().getLayouts().split(" ")) {

                    String formatDate = null;
                    Pattern p = Pattern.compile("\\%d\\{");
                    Matcher m = p.matcher(layout);

                    if (m.find()) {
                        formatDate = layout.substring(3, layout.length() - 1);
                        layout = layout.substring(0, 2);
                    } else {
                        formatDate = "d.m.y H:m:s";
                    }

                    switch (layout) {
                        case "%d":
                            SimpleDateFormat date = new SimpleDateFormat(formatDate);
                            preparedStatement.setString(i, date.format(new Date()));
                            i++;
                            break;

                        case "%p":
                            preparedStatement.setString(i, level.getLevelStr());
                            i++;
                            break;

                        case "%c":
                            preparedStatement.setString(i, clazz.getName());
                            i++;
                            break;

                        case "%m":
                            preparedStatement.setString(i, message);
                            i++;
                            break;
                        case "%t":
                            preparedStatement.setString(i, threadName);
                            i++;
                            break;
                        case "%s":
                            if (getPrintStacTrace(exception) != null) {
                                preparedStatement.setString(i, getPrintStacTrace(exception));
                                i++;
                            } else {
                                preparedStatement.setString(i, null);
                            }
                            break;
                    }
                }

                preparedStatement.executeUpdate();

            } catch (SQLException exc) {
                exc.printStackTrace();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
