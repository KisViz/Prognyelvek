import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class TombSemaphore {
    public static void main(String[] args) {
        double[] tomb = {-100, -100};
        Semaphore semaphore = new Semaphore(0);

        Thread elso = new Thread(new Szal1(tomb, semaphore));
        Thread masodik = new Thread(new Szal2(tomb, semaphore));

        elso.start();
        masodik.start();

        try {
            elso.join();
            masodik.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("A vegleges tomb: " + Arrays.toString(tomb));
    }
}

class Szal1 extends Thread {
    private double[] tomb;
    private Semaphore semaphore;

    public Szal1(double[] tomb, Semaphore semaphore) {
        this.tomb = tomb;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        this.tomb[0] = 200;
        System.out.println("Szal1 modositott tomb[0]: " + tomb[0]);
        semaphore.release();
        System.out.println("Szal1 elkuldte");
    }
}

class Szal2 extends Thread {
    private double[] tomb;
    private Semaphore semaphore;

    public Szal2(double[] tomb, Semaphore semaphore) {
        this.tomb = tomb;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println("Szal2 varakozik");
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.tomb[1] += this.tomb[0];
        System.out.println("Szal2 osszeadta tomb[1]: " + tomb[1]);
    }
}