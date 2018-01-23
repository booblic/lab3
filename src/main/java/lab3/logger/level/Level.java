package lab3.logger.level;

import javax.xml.bind.annotation.XmlTransient;

public class Level {
    private final static int ERROR_INT = 1;
    private final static int WARN_INT  = 2;
    private final static int INFO_INT  = 3;
    private final static int DEBUG_INT = 4;
    private final static int TRACE_INT = 5;

    @XmlTransient
    private int levelValue;

    private String levelStr;

    public final static Level ERROR = new Level(ERROR_INT, "ERROR");

    public final static Level WARN  = new Level(WARN_INT, "WARN");

    public final static Level INFO  = new Level(INFO_INT, "INFO");

    public final static Level DEBUG = new Level(DEBUG_INT, "DEBUG");

    public static final Level TRACE = new Level(TRACE_INT, "TRACE");

    private Level(int levelValue, String levelStr) {
        this.levelValue = levelValue;
        this.levelStr = levelStr;
    }

    public Level() {}

    public int getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(int levelValue) {
        this.levelValue = levelValue;
    }

    public String getLevelStr() {
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }

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
