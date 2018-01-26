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

@XmlType(name = "DataBaseAppender")
@XmlRootElement
public class DataBaseAppender extends Appender {

    private String url;
    private String userName;
    private String password;
    private String driver;
    private String sqlRequest;
    @XmlTransient
    private String columnOrder;

    public DataBaseAppender(String url, String userName, String password, String driver, String sqlRequest, Layout layout, Filter... filter) {
        super(layout, filter);
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driver = driver;
        this.sqlRequest = sqlRequest;
        columnOrder = getLayout().getLayouts();
    }

    public DataBaseAppender() {}

    public String getUrl() {
        return url;
    }

    @XmlElement(name = "URL")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    @XmlElement(name = "UserName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "Password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    @XmlAttribute(name = "Driver")
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getSqlRequest() {
        return sqlRequest;
    }

    @XmlElement(name = "SQLRequest")
    public void setSqlRequest(String sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

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

                for (String layout: columnOrder.split(" ")) {

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
