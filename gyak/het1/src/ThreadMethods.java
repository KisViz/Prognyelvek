public class ThreadMethods {

    public static void main(String[] args) {
        Thread thread = new MethodThread("Snicker");
        thread.start();
    }
}

class MethodThread extends Thread {

    MethodThread(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Current thread: " + this);  // megegyezik a Thread.currentThread() metódussal
        System.out.println(Thread.currentThread());  // megegyezik a Thread.currentThread() metódussal
        System.out.println("Id: " + this.threadId());
        System.out.println("Name: " + this.getName());
        System.out.println("Alive: " + this.isAlive());
        System.out.println("Daemon: " + this.isDaemon());
        System.out.println("Priority: " + this.getPriority());
    }
}
