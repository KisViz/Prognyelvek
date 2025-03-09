import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numberOfThreads = 5;

        CyclicBarrier barrier = new CyclicBarrier(5, new BarrierTask());

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new Task(barrier));
            thread.start();
        }
    }
}

class BarrierTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("A barrier feladata csak egyszer futott le.");
    }
}

class Task extends Thread {

    private final CyclicBarrier barrier;

    public Task(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.getName() + " dolgozik...");
            Thread.sleep(new Random().nextInt(1000));

            System.out.println(this.getName() + " a szinkronizációs pontban..(" +  (barrier.getNumberWaiting()+1) +")");
            barrier.await();

            System.out.println(this.getName() + " folytatja a munkát.");


            barrier.await();
            System.out.println(this.getName() + " megegyszer folytatja a munkát.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
