import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    static void print(long id, int iteration) {
        System.out.println(id + ": " + iteration);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);

        new Thread(() -> {
            print(Thread.currentThread().threadId(), 1);

            System.out.println(Thread.currentThread().threadId() + " is waiting.");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            System.out.println(Thread.currentThread().threadId() + " is sending a signal.");
//            semaphore.release();

            print(Thread.currentThread().threadId(), 2);
            print(Thread.currentThread().threadId(), 3);
            print(Thread.currentThread().threadId(), 4);
            print(Thread.currentThread().threadId(), 5);
            print(Thread.currentThread().threadId(), 6);
        }).start();

        new Thread(() -> {
            print(Thread.currentThread().threadId(), 1);
            print(Thread.currentThread().threadId(), 2);
            print(Thread.currentThread().threadId(), 3);
            print(Thread.currentThread().threadId(), 4);
            print(Thread.currentThread().threadId(), 5);
            System.out.println(Thread.currentThread().threadId() + " is sending a signal.");
            semaphore.release();

//            System.out.println(Thread.currentThread().threadId() + " is waiting.");
//            try {
//                semaphore.acquire();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            print(Thread.currentThread().threadId(), 6);
        }).start();
    }
}
