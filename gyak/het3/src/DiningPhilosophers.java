import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        Semaphore s1  = new Semaphore(1);
        Semaphore s2  = new Semaphore(1);
        Semaphore s3  = new Semaphore(1);
        Semaphore s4  = new Semaphore(1);
        Semaphore s5  = new Semaphore(1);
        Semaphore guard = new Semaphore(4);

        new Philosopher(s1, s2, guard).start();
        new Philosopher(s2, s3, guard).start();
        new Philosopher(s3, s4, guard).start();
        new Philosopher(s4, s5, guard).start();
        new Philosopher(s5, s1, guard).start();
    }
}

class Philosopher extends Thread{
    Semaphore leftFork;
    Semaphore rightFork;
    Semaphore guard;

    public Philosopher(Semaphore leftFork, Semaphore rightFork, Semaphore guard) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.guard = guard;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            eat();
        }
    }

    private void eat() {
        try {
            this.guard.acquire();

            this.leftFork.acquire();
            System.out.println(Thread.currentThread().threadId() + " picked up its left fork");
            this.rightFork.acquire();
            System.out.println(Thread.currentThread().threadId() + " picked up its right fork");

            Thread.sleep(100);
            System.out.println(Thread.currentThread().threadId() + " nom-nom...");

            this.rightFork.release();
            System.out.println(Thread.currentThread().threadId() + " put down its right fork");
            this.leftFork.release();
            System.out.println(Thread.currentThread().threadId() + " put down its right fork");

            this.guard.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
