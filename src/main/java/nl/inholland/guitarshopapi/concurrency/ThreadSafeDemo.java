package nl.inholland.guitarshopapi.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        new Thread(() -> {
            for (Integer i : list) {
                System.out.println("Thread 1 reads:  " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (Integer i : list) {
                list.add(i);
                System.out.println("Thread 2 adds:  " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println(list);
    }
}
