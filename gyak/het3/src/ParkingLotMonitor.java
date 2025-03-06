import java.util.ArrayList;
import java.util.LinkedList;

public class ParkingLotMonitor {
    public static void main(String[] args) {
        ParkingLot3 parkingLot = new ParkingLot3();
        ArrayList<Car3> asd = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Car3 uj = new Car3(parkingLot);
            uj.setName("Car-" + i);
            asd.add(uj);
        }

        for (Car3 elem : asd) {
            elem.start();
        }
    }
}

class ParkingLot3 {
    private LinkedList<String> autok;

    public ParkingLot3() {
        this.autok = new LinkedList<>();
    }

    public synchronized void enter(Thread thread) {
        while (autok.size() >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        autok.add(thread.getName());
    }

    public synchronized void leave(Thread thread) {
        autok.removeFirst();
        notify();
    }
}

class Car3 extends Thread {
    private ParkingLot3 parkingLot;

    public Car3(ParkingLot3 parkingLot1) {
        this.parkingLot = parkingLot1;
    }

    @Override
    public void run() {
        parkingLot.enter(Thread.currentThread());
        parkingLot.leave(Thread.currentThread());
    }
}