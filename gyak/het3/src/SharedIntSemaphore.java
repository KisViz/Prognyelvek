import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SharedIntSemaphore {
    public static void main(String[] args) {
        SharedInt1 sharedInt1 = new SharedInt1();
        ArrayList<Thread> modifiers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread uj = new Thread(new Modifier1(sharedInt1));
            uj.start();
            modifiers.add(uj);
        }

        for (Thread elem : modifiers) {
            try {
                elem.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Vegso ertek: " + sharedInt1.getSzam());
    }
}

class SharedInt1 {
    private int szam;
    private Semaphore semaphore;

    public SharedInt1() {
        this.semaphore = new Semaphore(1);
    }

    public void setValue(int newValue, Thread thread) {
        try {
            semaphore.acquire();
            this.szam = newValue;
            System.out.println("Szam modositasa id: " + thread.threadId());
            semaphore.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void decrement(Thread thread) {
        try {
            semaphore.acquire();
            System.out.println("Szam csokkentese " + this.szam + "->" + --this.szam + " id: " + thread.threadId());
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSzam() {
        return szam;
    }
}

class Modifier1 implements Runnable{
    private SharedInt1 sharedInt1;

    public Modifier1(SharedInt1 sharedInt) {
        this.sharedInt1 = sharedInt;
    }

    @Override
    public void run() {
        if (new Random().nextBoolean()) {
            sharedInt1.decrement(Thread.currentThread());
        } else {
            sharedInt1.setValue(new Random().nextInt(100), Thread.currentThread());
        }
    }
}
