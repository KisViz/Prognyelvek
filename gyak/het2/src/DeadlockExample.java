public class DeadlockExample {
    private final Object object1 = new Object();
    private final Object object2 = new Object();

    public void method1() {
        synchronized (object1) {
            System.out.println(Thread.currentThread().getName() + " got object1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + " got object2");
            }
        }
    }

    public void method2() {
        synchronized (object2) {
            System.out.println(Thread.currentThread().getName() + " got object2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + " got object1");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.method1();
            }
        }, "Thread-1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.method2();
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
