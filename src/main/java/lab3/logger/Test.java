package lab3.logger;

import lab3.logger.level.Level;

/**
 * Класс для тестирования логера
 * @author Кирилл
 * @version 1.0
 */
public class Test {
    private static final Logger logger = Logger.getLogger(Test.class);
    public void test() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> logger.log(Level.INFO, "Test.test()"));
            thread.start();
        }
    }
}
