import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLotCallableLock {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ParkingLot2 parkingLot2 = new ParkingLot2();

        for (int i = 0; i < 100; i++) {
            executorService.submit(new CarCallable2(parkingLot2));
        }

        executorService.shutdown();
    }
}

class ParkingLot2 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int szaml = 0;

    public ParkingLot2() {}

    public void enter() {
        lock.lock();
        while (szaml >= 10) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        szaml++;
        System.out.println(Thread.currentThread().getName() + " beallt a parkoloba");

        lock.unlock();
    }

    public void parking() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " parkol");
    }

    public void leave() {
        lock.lock();

        szaml--;
        System.out.println(Thread.currentThread().getName() + " elmegy");

        condition.signalAll();
        lock.unlock();
    }
}

class CarCallable2 implements Callable<Void> {
    private ParkingLot2 parkingLot2;

    public CarCallable2(ParkingLot2 parkingLot2) {
        this.parkingLot2 = parkingLot2;
    }


    @Override
    public Void call() throws Exception {

        parkingLot2.enter();
        parkingLot2.parking();
        parkingLot2.leave();

        return null;
    }
}