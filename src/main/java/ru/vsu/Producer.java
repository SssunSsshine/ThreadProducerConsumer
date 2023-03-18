package ru.vsu;

public class Producer implements Runnable {
    private Storage storage;


    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < Storage.BUFFER_MAX_SIZE; ++i) {
            try {
                storage.add(Integer.toString(i));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("Thread %s done producing%n%n",Thread.currentThread().getName());
    }
}
