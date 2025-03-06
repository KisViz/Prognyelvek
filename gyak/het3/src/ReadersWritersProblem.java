import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadersWritersProblem {
    public static void main(String[] args) {
        Book book  =  new Book(2);

        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            Writer writer = new Writer("Writer-" + i, book);
            writer.start();
            Reader reader = new Reader("Reader-" + i, book);
            reader.start();
        }
    }
}

class Book {
    ReentrantLock lock;
    Condition readers;
    Condition writers;

    int maxReaders; // maximum ennyi párhuzamos olvasás történhet
    int currentReaders; // az aktuális olvasók számát tárolja
    boolean isWriting; // igaz, ha éppen írás történik

    public Book(int maxReaders) {
        this.maxReaders = maxReaders;
        this.isWriting = false;
        lock = new ReentrantLock();
        readers = lock.newCondition();
        writers = lock.newCondition();
    }

    void read() {
        // az olvasó megpróbál hozzáférni az erőforráshoz
        lock.lock();
        while (isWriting == true || currentReaders == maxReaders) {
            try {
                // System.out.println(Thread.currentThread().getName() + " is waiting to read.");
                readers.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentReaders++;
        lock.unlock();

        // ez nem része a kritikus szakasznak, az olvasás mehet párhuzamosan
        System.out.println(Thread.currentThread().getName() + " is reading.. (" + currentReaders + ")");

        // az olvasó befejezte az erőforrás használatát
        lock.lock();
        currentReaders--;
        readers.signalAll();
        writers.signal();
        lock.unlock();
    }

    void write() {
        // az író megpróbál hozzáférni az erőforráshoz
        lock.lock();
        while (isWriting == true || currentReaders > 0) {
            try {
                // System.out.println(Thread.currentThread().getName() + " is waiting to write.");
                writers.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isWriting = true;
        lock.unlock();

        // ez nem része a kritikus szakasznak, de biztosan csak egy író fogja futtatni egy időpillanatban
        System.out.println(Thread.currentThread().getName() + " is writing..");

        // az író befejezte az erőforrás használatát
        lock.lock();
        isWriting = false;
        writers.signal();
        readers.signalAll();
        lock.unlock();
    }
}

class Reader extends Thread{
    Book book;

    public Reader(String name, Book book) {
        super(name);
        this.book = book;
    }

    @Override
    public void run() {
        this.book.read();
    }
}

class Writer extends Thread{
    Book book;

    public Writer(String name, Book book) {
        super(name);
        this.book = book;
    }

    @Override
    public void run() {
        this.book.write();
    }
}
