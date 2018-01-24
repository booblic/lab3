package lab3.logger.append;

import lab3.logger.filter.Filter;
import lab3.logger.layout.Layout;
import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.*;

@XmlType(name = "DataBaseAppender")
@XmlRootElement
public class DataBaseAppender extends Appender {

    private String url;
    private String userName;
    private String password;

    public DataBaseAppender(String url, String userName, String password, Layout layout, Filter... filter) {
        super(layout, filter);
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public DataBaseAppender() {}

    public String getUrl() {
        return url;
    }

    @XmlAttribute(name = "URL")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    @XmlAttribute(name = "UserName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @XmlAttribute(name = "Password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void log(Level level, Class clazz, String message) {
        System.out.println("YYYY");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try(Connection connection = DriverManager.getConnection(url, userName, password)) {
                System.out.println("Yess");

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO log_table(log) VALUES(?)");

                preparedStatement.setString(1, getLayout().messageBuilder(level, clazz, message));

                preparedStatement.executeUpdate();

            } catch (SQLException exc) {
                exc.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
