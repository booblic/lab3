package lab3.logger.main;

import lab3.logger.Logger;
import lab3.logger.Test;
import lab3.logger.level.Level;

import java.io.IOException;

/**
 * Класс для тестирования логера
 * @author Кирилл
 * @version 1.0
 */
public class Main {
    //private static final Logger logger = Logger.getLogger(Main.class, "config.xml");
    //private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String... args) {
        /*logger.log(Level.WARN, "sout");
        //logger.log(Level.WARN, "vasia");
        //logger.log(Level.INFO, "vasia");
        System.out.println("Hellow world!");
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.log(Level.WARN, "exeption!", e);
            //logger.log(Level.ERROR, "my message", e);
        }
        //MyClass m = new MyClass();
        //m.test();*/

        Test t = new Test();
        t.test();

        /*for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> logger.log(Level.WARN, "multy thread test"));
            thread.start();
        }*/
    }
}
