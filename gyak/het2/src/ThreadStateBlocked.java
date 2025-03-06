public class ThreadStateBlocked {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread1 = new BlockedThread(lock);
        Thread thread2 = new BlockedThread(lock);

//        Object lock = new Object();
//        Object lock2 = new Object();
//        Thread thread1 = new BlockedThread(lock);
//        Thread thread2 = new BlockedThread(lock2);

        thread1.start();
        Thread.sleep(100); // Biztosítjuk, hogy thread1 szerezze meg a zárat először
        thread2.start();

        Thread.sleep(100); // Rövid várakozás, hogy thread2 blokkolt állapotba kerüljön
        System.out.println(thread1.getState()); // TIMED_WAITING
        System.out.println(thread2.getState()); // BLOCKED
    }
}

class BlockedThread extends Thread {
    final Object lock;

    public BlockedThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("ez nem része a kritikus szakasznak");
        synchronized (lock) {
            try {
                Thread.sleep(500); // A másik szál blokkolva lesz, amíg a zár használatban van
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("ez nem része a kritikus szakasznak");
        synchronized (lock){
            //nem tortenik semmi
        }
    }
}
