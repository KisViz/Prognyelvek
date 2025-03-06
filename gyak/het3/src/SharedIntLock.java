import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class SharedIntLock {
    public static void main(String[] args) {
        SharedInt sharedInt = new SharedInt();
        ArrayList<Thread> modifiers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread modifier = new Thread(new Modifier(sharedInt));
            modifiers.add(modifier);
            modifier.start();
        }

        for (Thread elem : modifiers) {
            try {
                elem.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Vegso ertek: " + sharedInt.getSzam());

    }
}

class SharedInt {
    private int szam;

    private ReentrantLock lock;

    public SharedInt () {
        this.lock = new ReentrantLock();
    }

    public void setValue(int newValue, Thread thread) {
        lock.lock();
        this.szam = newValue;
        System.out.println("Szam modositasa id: " + thread.threadId());
        lock.unlock();
    }

    public void decrement(Thread thread) {
        lock.lock();
        System.out.println("Szam csokkentese " + this.szam + "->" + --this.szam + " id: " + thread.threadId());
        lock.unlock();
    }

    public int getSzam() {
        return szam;
    }
}

class Modifier implements Runnable {
    private SharedInt sharedInt;

    public Modifier(SharedInt sharedInt) {
        this.sharedInt = sharedInt;
    }

    @Override
    public void run() {
        if (new Random().nextBoolean()) {
            sharedInt.decrement(Thread.currentThread());
        } else {
            sharedInt.setValue(new Random().nextInt(100), Thread.currentThread());
        }
    }
}