import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0; i<10; i++) {
            executor.submit(new MyCallable());
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " - " + this);
        return 0;
    }
}
