import java.util.ArrayList;
import java.util.Random;

public class f1 {
    public static void main(String[] args) throws InterruptedException {
/*        SharedInt sharedInt = new SharedInt();
        ArrayList<Modifier> szalak = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Modifier uj = new Modifier(sharedInt);
            szalak.add(uj);
        }

        for (Modifier elem : szalak) {
            elem.run(); //nem run!!!!!!!!!
        }*/

        SharedInt sharedInt = new SharedInt();
        ArrayList<Thread> szalak = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            szalak.add(new Thread(new Modifier(sharedInt)));
        }

        for (Thread elem : szalak) {
            elem.start();
        }

        for (Thread elem : szalak) {
            elem.join();
        }

        System.out.println("A vegso ertek: " + sharedInt.getSzam());
    }
}

class SharedInt {
    private int szam;

    public SharedInt() {
    }

    public synchronized void setValue(int newValue) {
        this.szam = newValue;
        System.out.println("szam: " + szam);
    }

    public void decrement() {
        synchronized (this) {
            System.out.print("regi: " + szam);
            this.szam--;
            System.out.println(" uj: " + szam);
        }
    }

    public int getSzam() {
        return szam;
    }
}

class Modifier implements Runnable {
    public final SharedInt sharedInt;

    public Modifier(SharedInt sharedInt) {
        this.sharedInt = sharedInt;
    }

    @Override
    public void run() {
        Random random = new Random();

        if (random.nextBoolean()) {
            sharedInt.setValue(random.nextInt(10));
        } else {
            sharedInt.decrement();
        }
    }
}
