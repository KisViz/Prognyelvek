import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Pi2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<PiCallable2> callable2List = new ArrayList<PiCallable2>();
        List<Future<Point2D.Double>> futures = new ArrayList<Future<Point2D.Double>>();

        int K = 0, N = 100_000;

        for (int i = 0; i < N; i++) {
            callable2List.add(new PiCallable2());
        }

        try {
            futures = executorService.invokeAll(callable2List);

            for (Future<Point2D.Double> future : futures) {

                Point2D.Double pont = null;
                try {
                    pont = future.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                if (pont.x * pont.x + pont.y * pont.y <= 1) {
                    K++;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        double pi = 4.0 * K / N;
        System.out.println("Pi kozelitese: " + pi);

        executorService.shutdown();
    }
}

class PiCallable2 implements Callable<Point2D.Double> {

    @Override
    public Point2D.Double call() throws Exception {
        return new Point2D.Double(new Random().nextDouble(), new Random().nextDouble());
    }
}

