package lab3.logger.main;

import lab3.logger.Logger;
import lab3.logger.Test;
import lab3.logger.level.Level;

import java.io.IOException;


public class Main {
    //private static final Logger logger = Logger.getLogger(Main.class, "config.xml");
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String... args) {
        logger.log(Level.WARN, "System.out.println(\"Hellow world!\")");
        System.out.println("Hellow world!");
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "exeption!", e);
        }
        //Test t = new Test();
        //t.test();
    }
}
