import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    int number1;

    AtomicInteger number2 = new AtomicInteger();

    public void incNumberOne() {
        number1++;
    }

    public void incNumberTwo() {
        number2.incrementAndGet();
    }

    public static void main(String[] args) {
        AtomicCounter ac = new AtomicCounter();

        for (int i = 0; i < 10_000; i++) {
            Thread myThread = new AtomicThread(ac);
            myThread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final result (number1): " + ac.number1);
        System.out.println("Final result (number2): " + ac.number2.get());
    }
}

class AtomicThread extends Thread {
    AtomicCounter ac;

    AtomicThread(AtomicCounter ac) {
        this.ac = ac;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ac.incNumberOne();
        ac.incNumberTwo();
    }
}
