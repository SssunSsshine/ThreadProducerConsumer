package ru.vsu;

public class Consumer implements Runnable {
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < Storage.BUFFER_MAX_SIZE; ++i) {
            try {
                storage.remove(0);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("Thread %s done consuming%n%n", Thread.currentThread().getName());
    }
}
