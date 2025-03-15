import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<RandomCallable> callableList = new ArrayList<RandomCallable>();
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

        for(int i=0; i<10; i++) {
            callableList.add(new RandomCallable());
        }

        try {
            futures = executor.invokeAll(callableList);
            int sum = 0;
            for(Future<Integer> future : futures) {
                sum += future.get();
            }
            System.out.println("Result: " + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}

class RandomCallable implements Callable<Integer>{
    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(500);
        int res = new Random().nextInt(100);
        System.out.println(this + " is counting.. " + res);
        return res;
    }
}
