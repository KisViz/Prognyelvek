public class ExcessiveSynchronization {
    private final Object object = new Object();

    public synchronized void method1() { //zárolás impliciten a this-szel
        System.out.println(Thread.currentThread().getName() + " executing method1");
    }

    public void hello(){
        System.out.println("nem csinal semmit");
    }

    public void method2() {
        System.out.println("Ez a kiíratás nem része a kritikus szakasznak!");
        synchronized (this) { //object helyett this
            System.out.println(Thread.currentThread().getName() + " executing method2");
        }
    }
}
