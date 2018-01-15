package lab3.logger;

import lab3.logger.append.Appender;
import lab3.logger.level.Level;

import java.util.Objects;

public class LevelApender implements Comparable<LevelApender> {
    private final Level level;
    private final Appender appender;

    public LevelApender(Level level, Appender appender) {
        this.level = level;
        this.appender = appender;
    }

    public Level getLevel() {
        return level;
    }

    public Appender getAppender() {
        return appender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelApender that = (LevelApender) o;
        return Objects.equals(level.levelValue, that.level.levelValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level.levelValue);
    }

    @Override
    public int compareTo(LevelApender o) {
        return level.levelValue - o.level.levelValue;
    }
}
