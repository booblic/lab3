package lab3.logger.filter;

import lab3.logger.level.Level;

public class DefaultFilter extends Filter {

    public DefaultFilter(String defaultKey) {
        super(defaultKey);
    }

    @Override
    public boolean filter(Level level, Class clazz, String message) {
        return true;
    }
}
