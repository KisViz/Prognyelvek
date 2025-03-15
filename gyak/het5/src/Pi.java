import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Pi {
    public static void main(String[] args) {

        int K = 0, N = 100_000;

        ExecutorService executor = Executors.newCachedThreadPool();

        List<PiCallable> callableList = new ArrayList<PiCallable>();
        List<Future<Point2D.Double>> futures = new ArrayList<Future<Point2D.Double>>();

        for (int i = 0; i < N; i++) {
            callableList.add(new PiCallable());
        }


        try {
            futures = executor.invokeAll(callableList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Future<Point2D.Double> future : futures) {
            try {
                Point2D.Double pont = future.get();
                if (pont.x * pont.x + pont.y * pont.y <= 1) {
                    K++;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        double pi = 4.0 * K / N;
        System.out.println("Pi kozelitese: " + pi);

        executor.shutdown();
    }
}

class PiCallable implements Callable<Point2D.Double> {

    @Override
    public Point2D.Double call() throws Exception {
        return new Point2D.Double(new Random().nextDouble(), new Random().nextDouble());
    }
}

