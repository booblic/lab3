package lab3.logger.level;

public class Level {
    public final static int ERROR_INT = 10000;
    public final static int WARN_INT  = 20000;
    public final static int INFO_INT  = 30000;
    public final static int DEBUG_INT = 40000;
    public final static int TRACE_INT = 50000;

    public int level;
    public String levelStr;

    final static public Level ERROR = new Level(ERROR_INT, "ERROR");

    final static public Level WARN  = new Level(WARN_INT, "WARN");

    final static public Level INFO  = new Level(INFO_INT, "INFO");

    final static public Level DEBUG = new Level(DEBUG_INT, "DEBUG");

    public static final Level TRACE = new Level(TRACE_INT, "TRACE");

    public Level(int level, String levelStr) {
        this.level = level;
        this.levelStr = levelStr;
    }
}
