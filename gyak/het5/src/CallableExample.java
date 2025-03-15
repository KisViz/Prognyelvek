import java.util.concurrent.Callable;

public class CallableExample {
    public static void main(String[] args) {
        Callable<Integer> callableTask = new ExampleCallable();
        try {
            System.out.println("Return value: " + callableTask.call() + " executed by: " + Thread.currentThread());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ExampleCallable implements Callable<Integer> {

    @Override
    public Integer call()  { // a run() nem dobhat kivételt
        System.out.println(Thread.currentThread().getName() + " is counting..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int result = (int) (Math.random() * 100);
        System.out.println(Thread.currentThread().getName() + "'s result: " + result);
        return result;
    }
}
