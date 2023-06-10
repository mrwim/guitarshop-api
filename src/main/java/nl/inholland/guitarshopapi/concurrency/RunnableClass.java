package nl.inholland.guitarshopapi.concurrency;

public class RunnableClass implements Runnable {

    public static final String THREAD_NAME = "This thread has name ";

    public static void main(String[] args) {
        System.out.println(THREAD_NAME + Thread.currentThread().getName());
        RunnableClass runnable = new RunnableClass();
        Thread thread = new Thread(runnable);
        thread.start();

        new Thread(() -> System.out.println(THREAD_NAME + Thread.currentThread().getName())).start();
    }

    @Override
    public void run() {
        System.out.println(THREAD_NAME + Thread.currentThread().getName());
    }
}
