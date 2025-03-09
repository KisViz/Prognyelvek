import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ResourceCyclicBarrier {
    public static void main(String[] args) {
        int szalakSzama = 4;

        Resource resource = new Resource(szalakSzama);
        ArrayList<Calculator> calculators = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(szalakSzama, new Summer(calculators));

        int darabmeret = resource.getArray().length / szalakSzama;
        for (int i = 0; i < szalakSzama; i++) {
            //a kezdet az mindig i * a meret
            int kezdo = i * darabmeret;
            // a vege, ha sz utolsonal vagyunk, akkor alegvege, egyebkent mindig a kezdo + meret
            int vege = i == szalakSzama - 1 ? resource.getArray().length : kezdo + darabmeret;
            Calculator calculator = new Calculator(resource, kezdo, vege, cyclicBarrier);
            calculators.add(calculator);
            calculator.start();
        }
    }
}

class Resource {
    private int[] array;

    public Resource(int szalakSzama) {
        this.array = new int[szalakSzama * 100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    public int[] getArray() {
        return array;
    }
}

class Calculator extends Thread {
    private Resource resource;
    private int kezdo;
    private int vege;
    private CyclicBarrier cyclicBarrier;

    private int sum;

    public Calculator(Resource resource, int kezdo, int vege, CyclicBarrier cyclicBarrier) {
        this.resource = resource;
        this.kezdo = kezdo;
        this.vege = vege;
        this.cyclicBarrier = cyclicBarrier;

        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i = kezdo; i < vege; i++) {
            sum += resource.getArray()[i];
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSum() {
        return sum;
    }
}

class Summer implements Runnable {
    ArrayList<Calculator> szalak;

    public Summer(ArrayList<Calculator> szalak) {
        this.szalak = szalak;
    }

    @Override
    public void run() {
        int sum = 0;
        for (Calculator szal : szalak) {
            sum += szal.getSum();
        }

        System.out.println("A vegleges osszeg: " + sum);
    }
}