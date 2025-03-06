public class f2 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable(5);
        MyThread myThread = new MyThread(myRunnable);
        MyRunnable myRunnable2 = new MyRunnable(5);
        MyThread myThread2 = new MyThread(myRunnable2);

        myThread.setName("elso");
        myThread2.setName("masodik");

        myThread.start();
        myThread2.start();
    }

}

class MyRunnable implements Runnable{
    public int count;
    public MyRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = count; i >= 0; i--) {
            System.out.println(i + " " + Thread.currentThread().getName());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

class MyThread extends Thread {
    public MyRunnable myRunnable;
    public MyThread(MyRunnable myRunnable) {
//        this.myRunnable = myRunnable;
//        az os konstruktoranak kell atadni, mint ha siman mainben hivnad
        super(myRunnable);
    }

// ez nem kell, mert akkor nem a my runnable run-jat hivja meg
//    @Override
//    public void run() {
//    }
}