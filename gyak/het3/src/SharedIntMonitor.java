import java.util.ArrayList;
import java.util.Random;

public class SharedIntMonitor {
    public static void main(String[] args) {
        SharedInt2 sharedInt2 = new SharedInt2();
        ArrayList<Thread> modifiers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread uj = new Thread(new Modifier2(sharedInt2));
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

        System.out.println("Vegso ertek: " + sharedInt2.getSzam());
    }
}

class SharedInt2 {
    private int szam;

    public SharedInt2() {}

    public synchronized void setValue(int newValue, Thread thread) {

        this.szam = newValue;
        System.out.println("Szam modositasa id: " + thread.threadId());
    }

    public synchronized void decrement(Thread thread) {
        System.out.println("Szam csokkentese " + this.szam + "->" + --this.szam + " id: " + thread.threadId());
    }

    public int getSzam() {
        return szam;
    }
}

class Modifier2 implements Runnable{
    private SharedInt2 sharedInt2;

    public Modifier2(SharedInt2 sharedInt) {
        this.sharedInt2 = sharedInt;
    }

    @Override
    public void run() {
        if (new Random().nextBoolean()) {
            sharedInt2.decrement(Thread.currentThread());
        } else {
            sharedInt2.setValue(new Random().nextInt(100), Thread.currentThread());
        }
    }
}