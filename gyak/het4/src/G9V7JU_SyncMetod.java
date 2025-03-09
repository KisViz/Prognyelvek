public class G9V7JU_SyncMetod {
    public static void main(String[] args) {
        ParkingLot3 parkingLot = new ParkingLot3();

        for (int i = 0; i < 100; i++) {
            Car3 uj = new Car3(parkingLot);
            uj.setName(i + ". auto");
            uj.start();
        }
    }
}

class ParkingLot3  {
    private int foglalt;

    public ParkingLot3() {
        foglalt = 0;
    }

    public synchronized void enter() {
        while (foglalt >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        foglalt++;
    }

    public void parking() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void leave() {
        foglalt--;
        notifyAll();
    }
}

class Car3 extends Thread{
    private ParkingLot3 parkingLot;

    public Car3(ParkingLot3 parkingLot) {
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


