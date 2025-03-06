import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLotLock {
    public static void main(String[] args) {
        ParkingLot1 parkingLot1 = new ParkingLot1();
        ArrayList<Car1> arrayList  =new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Car1 uj = new Car1(parkingLot1);
            uj.setName("Car-" + i);
            arrayList.add(uj);
        }

        for (Car1 elem : arrayList) {
            elem.start();
        }
    }
}

class ParkingLot1 {
    private LinkedList<String> autok;

    private ReentrantLock lock;
    private Condition var;

    public ParkingLot1() {
        this.autok = new LinkedList<>();

        this.lock = new ReentrantLock();
        this.var = lock.newCondition();
    }

    public void enter(Thread thread) {
        lock.lock();
        while (autok.size() >= 10) {
            try {
                var.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        autok.add(thread.getName());
        lock.unlock();
    }

    public void leave(Thread thread) {
        lock.lock();
        autok.removeFirst();
        var.signal();
        lock.unlock();
    }
}

class Car1 extends Thread {
    private ParkingLot1 parkingLot1;

    public Car1(ParkingLot1 parkingLot1) {
        this.parkingLot1 = parkingLot1;
    }

    @Override
    public void run() {
        parkingLot1.enter(Thread.currentThread());
        parkingLot1.leave(Thread.currentThread());
    }
}