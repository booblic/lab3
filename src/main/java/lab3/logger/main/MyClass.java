package lab3.logger.main;

import lab3.logger.Logger;
import lab3.logger.level.Level;

public class MyClass {
    private static final Logger logger = Logger.getLogger(MyClass.class);
    public void test() {
        logger.log(Level.ERROR, "System.out.println(\"Hellow world!\")");
    }
}
