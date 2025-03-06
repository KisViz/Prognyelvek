public class ThreadStates {

    public static void main(String[] args) {
        Thread thread1 = new Thread();
        System.out.println(thread1.getState()); // NEW

        Thread thread2 = new RunnableThread();
        thread2.start();

        Thread thread3 = new TimedWaitingThread();
        thread3.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread3.getState()); // TIMED_WAITING

        System.out.println(thread2.getState()); // TERMINATED
    }
}

class RunnableThread extends Thread {
    @Override
    public void run() {
        System.out.println(this.getState()); // RUNNABLE
    }
}

class TimedWaitingThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}