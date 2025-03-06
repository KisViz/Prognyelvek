import java.util.Random;
import java.util.concurrent.Semaphore;

public class ParkingLot {
    public static void main(String[] args) {
        Semaphore parkingLot = new Semaphore(4);

        for (int i = 1; i <= 10; i++) {
            new Vehicle(parkingLot).start();
        }
    }
}

class Vehicle extends Thread {
    private final Semaphore parkingLot;

    public Vehicle(Semaphore parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            System.out.println("Vehicle " + Thread.currentThread().threadId() + " needs 2 spaces.");
            parkingLot.acquire(2);
            System.out.println("Vehicle " + Thread.currentThread().threadId() + " has parked." );

            Thread.sleep(100);

            System.out.println("Vehicle " + Thread.currentThread().threadId() + " has left.");
            parkingLot.release(2);
        } catch (InterruptedException e) {
            System.err.println("Vehicle " + Thread.currentThread().threadId() + " was interrupted.");
        }
    }
}
