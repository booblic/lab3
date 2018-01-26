package lab3.logger.main;

import lab3.logger.Logger;
import lab3.logger.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author VYZH
 * @since 25.01.2018
 */
public class ParallelTest {
    public static void main(String[] args) throws InterruptedException {

        List<Callable<Object>> runnables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            runnables.add(Executors.callable(new Runnable() {
                @Override
                public void run() {
                    try {
                        Logger logger = Logger.getLogger(Main.class, "config.xml");
                        logger.log(Level.ERROR, "message from Thread: " + Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        ExecutorService es = Executors.newFixedThreadPool(1010);
        es.invokeAll(runnables);
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
    }
}
