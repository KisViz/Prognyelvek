import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerProblemWithLock {
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
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.list = new ArrayList<Integer>();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    void deposit(Thread thread) {
        lock.lock();
        while (list.size() == maxSize) { // Ha a buffer tele van, várakozunk
            System.out.println(thread.getName() + " is waiting for free space.");
            try {
                notFull.await();
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
        System.out.println(thread.getName() + " buffer: " + list);
        notEmpty.signal();
        lock.unlock();
    }

    void extract(Thread thread) {
        lock.lock();
        while (list.isEmpty()) { // Ha a buffer üres, várakozunk
            System.out.println(thread.getName() + " is waiting for an item.");
            try {
                notEmpty.await();
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
        System.out.println(thread.getName() + " buffer: " + list);
        notFull.signal();
        lock.unlock();
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
        buffer.deposit(this);
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
        buffer.extract(this);
    }
}
