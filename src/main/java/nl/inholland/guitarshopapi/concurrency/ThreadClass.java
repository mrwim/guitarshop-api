package nl.inholland.guitarshopapi.concurrency;

public class ThreadClass extends Thread {
    public static void main(String[] args) {
        System.out.println("This thread has name " + Thread.currentThread().getName());
        ThreadClass thread = new ThreadClass();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("This thread has name " + Thread.currentThread().getName());
    }
}
