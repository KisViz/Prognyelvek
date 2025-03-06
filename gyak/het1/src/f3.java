import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class f3 {
    public static void main(String[] args) throws InterruptedException {
        int szalakSzama = 16;

        Resource resource = new Resource(szalakSzama);
        List<Calculator> szalak = new ArrayList<>();

        int ossz = resource.szamok.length;
        int darabMeret = ossz / szalakSzama;

        for (int i = 0; i < szalakSzama; i++) {
            int kezdo = i * darabMeret; //az eleje a szal szamaszor a meret
            int vege = (i == szalakSzama - 1) ? resource.szamok.length : kezdo + darabMeret;
                     //magyarul az utolso-e     ha igen akkor a vege     ha nem, akkor az adott kezdet + a darab merete
            Calculator adott = new Calculator(resource,kezdo,vege);
            adott.start();
            szalak.add(adott);
        }

        int osszeg = 0;
        for (Calculator elem : szalak) {
            elem.join(); //bevarjuk a szalat
            osszeg += elem.getSzum();
        }

        System.out.println("Osszesen: " + osszeg);
    }
}

class Resource extends Thread {
    public int[] szamok;
    public Resource(int szalakSzama) {
        szamok = new int[szalakSzama * 100];
        for (int i = 1; i <= szalakSzama * 100; i++) {
            this.szamok[i - 1] = i;
        }
    }
}

class Calculator extends Thread {
    public Resource resource;
    public int kezdo;
    public int vege;
    public int szum;
    public Calculator(Resource resource, int kezdo, int vege) {
        this.resource = resource;
        this.kezdo = kezdo;
        this.vege = vege;
        this.szum = 0;
    }

    @Override
    public void run() {
        for (int i = kezdo; i < vege; i++) {
            szum += resource.szamok[i];
        }
    }

    public int getSzum() {
        return szum;
    }
}