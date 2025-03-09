import java.util.concurrent.Semaphore;

public class G9V7JU_Semaphore {
    public static void main(String[] args) {
        ParkingLot1 parkingLot = new ParkingLot1();

        for (int i = 0; i < 100; i++) {
            Car1 uj = new Car1(parkingLot);
            uj.setName(i + ". auto");
            uj.start();
        }
    }
}

class ParkingLot1  {
    private Semaphore semaphore;

    public ParkingLot1() {
        semaphore = new Semaphore(10);
    }

    public void enter() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void parking() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void leave() {
        semaphore.release();
    }
}

class Car1 extends Thread{
    private ParkingLot1 parkingLot;

    public Car1(ParkingLot1 parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.enter();
        System.out.println(this.getName() + " belepett");
        parkingLot.parking();
        System.out.println(this.getName() + " leparkolt");
        parkingLot.leave();
        System.out.println(this.getName() + " elment");
    }
}