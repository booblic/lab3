package lab3.logger.level;

import javax.xml.bind.annotation.XmlTransient;

//@XmlType(propOrder = {"ERROR", "WARN", "INFO", "DEBUG", "TRACE"}, name = "level")
public class Level {
    public final static int ERROR_INT = 1;
    public final static int WARN_INT  = 2;
    public final static int INFO_INT  = 3;
    public final static int DEBUG_INT = 4;
    public final static int TRACE_INT = 5;

    public int levelValue;
    public String levelStr;

    final static public Level ERROR = new Level(ERROR_INT, "ERROR");

    final static public Level WARN  = new Level(WARN_INT, "WARN");

    final static public Level INFO  = new Level(INFO_INT, "INFO");

    final static public Level DEBUG = new Level(DEBUG_INT, "DEBUG");

    public static final Level TRACE = new Level(TRACE_INT, "TRACE");

    public Level(int levelValue, String levelStr) {
        this.levelValue = levelValue;
        this.levelStr = levelStr;
    }

    public Level() {}

    public static Level valueOf(String levelStr) {
        switch (levelStr) {
            case "ERROR" : return ERROR;
            case "WARN" : return WARN;
            case "INFO" : return INFO;
            case "DEBUG" : return DEBUG;
            case "TRACE" : return TRACE;
        }
        throw new IllegalArgumentException("Unsupported level");
    }
}
