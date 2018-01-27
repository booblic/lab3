package lab3.logger.level;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Класс представляющий уровень логирования
 * @author Кирилл
 * @version 1.0
 */
public class Level {

    /**
     * Числовая константа, соответствующая уровню логирования ERROR
     */
    private final static int ERROR_INT = 1;

    /**
     * Числовая константа, соответствующая уровню логирования WARN
     */
    private final static int WARN_INT  = 2;

    /**
     * Числовая константа, соответствующая уровню логирования INFO
     */
    private final static int INFO_INT  = 3;

    /**
     * Числовая константа, соответствующая уровню логирования DEBUG
     */
    private final static int DEBUG_INT = 4;

    /**
     * Числовая константа, соответствующая уровню логирования TRACE
     */
    private final static int TRACE_INT = 5;

    /**
     * Числовое значение, соответсвующее уровню логирования
     */
    @XmlTransient
    private int levelValue;

    /**
     * Строковое значение, соответсвующее уровню логирования
     */
    private String levelStr;

    /**
     * Объект уровня логирования ERROR
     */
    public final static Level ERROR = new Level(ERROR_INT, "ERROR");

    /**
     * Объект уровня логирования WARN
     */
    public final static Level WARN  = new Level(WARN_INT, "WARN");

    /**
     * Объект уровня логирования INFO
     */
    public final static Level INFO  = new Level(INFO_INT, "INFO");

    /**
     * Объект уровня логирования DEBUG
     */
    public final static Level DEBUG = new Level(DEBUG_INT, "DEBUG");

    /**
     * Объект уровня логирования TRACE
     */
    public static final Level TRACE = new Level(TRACE_INT, "TRACE");

    /**
     * Конструктор уровня логирования
     * @param levelValue - числовое значение, соответсвующее уровню логирования
     * @param levelStr - строковое значение, соответсвующее уровню логирования
     */
    private Level(int levelValue, String levelStr) {
        this.levelValue = levelValue;
        this.levelStr = levelStr;
    }

    /**
     * Конструктор по-умолчанию, для рефлексивного создания объекта после анмаршлинга
     */
    public Level() {}

    /**
     * Геттер числового значения, соответсвующего уровню логирования
     * @return levelValue - числовое значение, соответсвующее уровню логирования
     */
    public int getLevelValue() {
        return levelValue;
    }

    /**
     * Геттер числового значения, соответсвующего уровню логирования
     * @param levelValue - числовое значение, соответсвующее уровню логирования
     */
    public void setLevelValue(int levelValue) {
        this.levelValue = levelValue;
    }

    /**
     * Геттер строкового значения, соответсвующего уровню логирования
     * @return levelStr - строковое значение, соответсвующее уровню логирования
     */
    public String getLevelStr() {
        return levelStr;
    }

    /**
     * Геттер строкового значения, соответсвующего уровню логирования
     * @param levelStr - строковое значение, соответсвующее уровню логирования
     */
    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }

    /**
     * Метод для класса LevelAdapter extends XmlAdapter String, Level, служащий для анмаршлинга
     * @param levelStr - строковое значение, соответсвующее уровню логирования
     * @return (ERROR/WARN/INFO/DEBUG/TRACE) - объект уровня логирования
     */
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
