package ru.vsu;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private int size;
    public static final int BUFFER_MAX_SIZE = 50;
    private List<String> buffer;

    public Storage() {
        this.buffer = new ArrayList<>();
        size = 0;
    }

    public synchronized void add(String value) throws InterruptedException {
        while (size == BUFFER_MAX_SIZE) {
            wait();
        }
        buffer.add(value);
        size++;
        System.out.printf("Thread %s put in the storage.%nAmount of objects = %d%n%n", Thread.currentThread().getName(), size);
        Thread.sleep(1000);
        notify();
    }

    public synchronized void remove(int i) throws InterruptedException {
        while (size == 0) {
            wait();
        }
        buffer.remove(i);
        size--;
        System.out.printf("Thread %s take from the storage.%nAmount of objects = %d%n%n", Thread.currentThread().getName(), size);
        Thread.sleep(1000);
        notify();
    }

    public List<String> getBuffer() {
        return buffer;
    }

    public int getSize() {
        return size;
    }
}
