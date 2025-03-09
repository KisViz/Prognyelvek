import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class G9V7JU_Lock {
    public static void main(String[] args) {
        ParkingLot2 parkingLot = new ParkingLot2();

        for (int i = 0; i < 100; i++) {
            Car2 uj = new Car2(parkingLot);
            uj.setName(i + ". auto");
            uj.start();
        }
    }
}

class ParkingLot2  {
    private ReentrantLock lock;
    private Condition var;
    private int foglalt;

    public ParkingLot2() {
        lock = new ReentrantLock();
        var = lock.newCondition();
        foglalt = 0;
    }

    public void enter() {
        lock.lock();
        while (foglalt >= 10) {
            try {
                var.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        foglalt++;
        lock.unlock();
    }

    public void parking() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void leave() {
        lock.lock();
        foglalt--;
        var.signal();
        lock.unlock();
    }
}

class Car2 extends Thread{
    private ParkingLot2 parkingLot;

    public Car2(ParkingLot2 parkingLot) {
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


