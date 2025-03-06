import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class ParkingLotSemaphore {
    public static void main(String[] args) {
        ParkingLot2 parkingLot2 = new ParkingLot2();
        ArrayList<Car2> asd = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Car2 uj = new Car2(parkingLot2);
            uj.setName("Car-" + i);
            asd.add(uj);
        }

        for (Car2 elem : asd) {
            elem.start();
        }
    }
}

class ParkingLot2 {
    //emiatt lesz sok error, mert az iras/olvasas nem atomi, ezert nem szereti
    //egy binaris szemafor kene meg, hogy jol mukodjon
    //de nem lenne kotelezo egyebkent se, mert maga a szemafor az "eroforras"
    //ugyhogy hagyom:")
    private LinkedList<String> autok;

    Semaphore parkolo;

    public ParkingLot2() {
        this.autok = new LinkedList<>();

        this.parkolo = new Semaphore(10);
    }

    public void enter(Thread thread) {
        try {
            parkolo.acquire();
            autok.add(thread.getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void leave(Thread thread) {
        if (!autok.isEmpty()) {

            autok.removeFirst();
            parkolo.release();
        }
    }
}

class Car2 extends Thread {
    private ParkingLot2 parkingLot;

    public Car2(ParkingLot2 parkingLot1) {
        this.parkingLot = parkingLot1;
    }

    @Override
    public void run() {
        parkingLot.enter(Thread.currentThread());
//        try {
//            Thread.sleep(200); //tudom nem szabadna:')
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        parkingLot.leave(Thread.currentThread());
    }
}