package lab3.logger.main;

import lab3.logger.Logger;
import lab3.logger.level.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String... args) {
        logger.log(Level.INFO, " System.out.println(\"Hellow world!\")");
        System.out.println("Hellow world!");
    }
}