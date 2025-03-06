public class f1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello");

        Thread.currentThread().join();

        System.out.println("Word!");
    }
    /*
    kiirja a hellot, aztan meghivja sajat magara a joint,
    igy megvarja, hogy sajat maga befejezodjon, de ez ugye nem
    lehetseges, ezert vegtelen ciklus lesz
    */
}
