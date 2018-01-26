package lab3.logger;

import lab3.logger.level.Level;

public class Test {
    private static final Logger logger = Logger.getLogger(Test.class, "config.xml");
    public void test() {
        logger.log(Level.WARN, "test");
    }
}
