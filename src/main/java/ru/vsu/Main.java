package ru.vsu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int THREADS_AMOUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        Storage stringStorage = new Storage();
        Producer producer = new Producer(stringStorage);
        Consumer consumer = new Consumer(stringStorage);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREADS_AMOUNT; i++) {
            threads.add(new Thread(producer));
            threads.add(new Thread(consumer));
        }

        threads.stream().forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }
}