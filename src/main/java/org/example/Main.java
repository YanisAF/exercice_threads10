package org.example;

import java.util.concurrent.atomic.AtomicLong;


public class Main {
    private final static AtomicLong atomicLong = new AtomicLong(1);
    public static void main(String[] args) throws InterruptedException {

        Thread[] treads = createThreads();
        for (Thread t : treads) t.start();
        for (Thread t : treads) t.join();
        System.out.println("Valeur finale : "+atomicLong.get());



    }

    private static Thread[] createThreads() {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int multiplicateur = i + 2;
            threads[i] = new Thread(() -> {
                long value = atomicLong.updateAndGet(x -> x * multiplicateur);
                System.out.println(Thread.currentThread().getName() + " multiplicateur : " +multiplicateur+ " = "+value);
            });
        } return threads;
    }

}