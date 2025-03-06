import java.util.Random;

public class f2 {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        for (int i = 0; i < 100; i++) {
            new Car(parkingLot).start();
        }
    }
}

class ParkingLot {
    public int helyek;

    public ParkingLot() {
        this.helyek = 10;
    }

    public synchronized void enter() {
        while (this.helyek == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.helyek--;
        System.out.println("helyek: " + this.helyek);
        notifyAll();
    }

    public synchronized void leave() {
        this.helyek++;
        System.out.println("helyek: " + this.helyek);
        notifyAll();
    }
}

class Car extends Thread {
    public final ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.enter();

        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        parkingLot.leave();
    }
}