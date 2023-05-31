package nl.inholland.guitarshopapi.concurrency;

public class Prediction {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() ->
            {
                for (int j = 0; j < 3; j++) {
                    System.out.println("Thread " + Thread.currentThread().getName() + "j = " + j);
                }
            }).start();
            new Thread(() -> System.out.println(Thread.currentThread().getName() + " Hello World!")).start();

        }
    }
}
