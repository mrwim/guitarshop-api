package nl.inholland.guitarshopapi.concurrency;

public class RunnableClass implements Runnable {
    public static void main(String[] args) {
        System.out.println("This thread has name " + Thread.currentThread().getName());
        RunnableClass runnable = new RunnableClass();
        Thread thread = new Thread(runnable);
        thread.start();

        new Thread(() -> System.out.println("This thread has name " + Thread.currentThread().getName())).start();
    }

    @Override
    public void run() {
        System.out.println("This thread has name " + Thread.currentThread().getName());
    }
}
