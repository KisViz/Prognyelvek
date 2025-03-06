public class HelloWorld {

    public static void main(String[] args) {
        Thread thread = new ExampleThread();
        Thread runnable = new Thread(new ExampleRunnable(10));

        thread.start();
        runnable.start();
    }
}

class ExampleThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello World - Thread");
        }
    }
}

class ExampleRunnable implements Runnable {
    int num;

    public ExampleRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello World - Runnable");
        }
    }
}
