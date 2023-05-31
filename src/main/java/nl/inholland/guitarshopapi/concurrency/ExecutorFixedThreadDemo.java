package nl.inholland.guitarshopapi.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFixedThreadDemo {

    private int counter = 0;

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            ExecutorFixedThreadDemo executorsDemo = new ExecutorFixedThreadDemo();
            for (int i = 0; i < 10; i++) {
                executorService.submit(executorsDemo::incrementAndPrint);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private void incrementAndPrint() {
        System.out.println(Thread.currentThread().getName() + ": " + counter++);
    }
}
