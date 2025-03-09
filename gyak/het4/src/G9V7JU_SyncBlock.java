public class G9V7JU_SyncBlock {
    public static void main(String[] args) {
        ParkingLot4 parkingLot = new ParkingLot4();

        for (int i = 0; i < 100; i++) {
            Car4 uj = new Car4(parkingLot);
            uj.setName(i + ". auto");
            uj.start();
        }
    }
}

class ParkingLot4  {
    private int foglalt;

    public ParkingLot4() {
        foglalt = 0;
    }

    public void enter() {
        synchronized (this) {
            while (foglalt >= 10) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            foglalt++;
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
        synchronized (this) {
            foglalt--;
            notifyAll();
        }
    }
}

class Car4 extends Thread{
    private ParkingLot4 parkingLot;

    public Car4(ParkingLot4 parkingLot) {
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



