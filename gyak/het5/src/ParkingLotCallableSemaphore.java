import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ParkingLotCallableSemaphore {
    public static void main(String[] args) {
        ParkingLot1 parkingLot1 = new ParkingLot1();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executor.submit(new CarCallable1(parkingLot1));
        }

        executor.shutdown();
    }
}

class ParkingLot1 {
    private Semaphore semaphore = new Semaphore(10);

    public ParkingLot1() {}

    public void enter() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " beallt a parkoloba");
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
        System.out.println(Thread.currentThread().getName() + " elmegy");
        semaphore.release();
    }
}

class CarCallable1 implements Callable<Void> {
    private ParkingLot1 parkingLot1;

    public CarCallable1(ParkingLot1 parkingLot1) {
        this.parkingLot1 = parkingLot1;
    }

    @Override
    public Void call() throws Exception {

        parkingLot1.enter();
        parkingLot1.parking();
        parkingLot1.leave();

        return null;
    }
}