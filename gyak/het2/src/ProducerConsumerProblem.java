import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ProducerConsumerProblem {
    public static void main(String[] args) {
        int numberOfProducers = 20;
        int numberOfConsumers = 10;
        Buffer buffer = new Buffer(10);

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfProducers; i++) {
            Producer producer = new Producer("Producer-" + i, buffer);
            threads.add(producer);
        }
        for (int i = 0; i < numberOfConsumers; i++) {
            Consumer consumer = new Consumer("Consumer-" + i, buffer);
            threads.add(consumer);
        }

        // Szálak indítása véletlenszerű sorrendben
        Collections.shuffle(threads);
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class Buffer {
    private final int maxSize;
    private final ArrayList<Integer> list;

    Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.list = new ArrayList<Integer>();
    }

    synchronized void deposit() {
        while (list.size() == maxSize) { // Ha a buffer tele van, várakozunk
            System.out.println(Thread.currentThread().getName() + " is waiting for free space.");
            try {
                wait();
                // System.out.println(thread.getState()); // WAITING
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int item = new Random().nextInt(100); // Elem hozzáadása
        try {
            Thread.sleep(100); // Szimuláljuk a munkát
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        list.add(item);
        System.out.println(Thread.currentThread().getName() + " buffer: " + list);
        notifyAll();
    }

    synchronized void extract() {
        while (list.isEmpty()) { // Ha a buffer üres, várakozunk
            System.out.println(Thread.currentThread().getName() + " is waiting for an item.");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        list.removeFirst(); // Elem kivétele a pufferből
        try {
            Thread.sleep(100); // Szimuláljuk a munkát
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " buffer: " + list);
        notifyAll();
    }
}

class Producer extends Thread {
    private final Buffer buffer;

    Producer(String name, Buffer buffer) {
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.deposit();
    }
}

class Consumer extends Thread {
    private final Buffer buffer;

    Consumer(String name, Buffer buffer) {
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.extract();
    }
}
