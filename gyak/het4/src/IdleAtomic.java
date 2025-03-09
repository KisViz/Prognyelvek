import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class IdleAtomic {

    public static void main(String[] args) {
        AtomicReference<String> valt = new AtomicReference<>("Idle");

        for (int i = 0; i < 10; i++) {
            Idle uj = new Idle(valt);
            uj.setName(i + ". szal");
            uj.start();
        }

    }
}

class Idle extends Thread {
    AtomicReference<String> valt;

    public Idle(AtomicReference<String> valt) {
        this.valt = valt;
    }

    @Override
    public void run() {
        boolean elso = true;
        while (!valt.compareAndSet("Idle", "InProgress")) {
            if (elso) {
                System.out.println(this.getName() + " varakozik");
                elso = false;
            }
        }

        System.out.println(this.getName() + " dolgozik");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        valt.set("Idle");
    }
}