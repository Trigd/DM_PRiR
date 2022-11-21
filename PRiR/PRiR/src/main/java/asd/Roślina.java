package asd;

import java.util.Random;
public class Roślina extends Thread {
    static int LAS = 1;
    static int ZASADZENIE = 2;
    static int ROZROST = 3;
    static int ROZKWIT = 4;
    static int WIEDNIECIE = 5;
    static int FOTOSYNTEZA = 100;
    static int ILOSC_SLONCA = 50;

    int numer;
    int energia;
    int stan;
    Las l;
    Random rand;

    public Roślina(int numer, int energia, Las l) {
        this.numer = numer;
        this.energia = energia;
        this.stan = ROZROST;
        this.l = l;
        rand = new Random();
    }

    public void run() {
        while (true) {
            if (stan == LAS) {
                if (rand.nextInt(2) == 1) {
                    stan = ZASADZENIE;
                    energia = FOTOSYNTEZA;
                    System.out.println("Nasionko " + numer + " oczekuje odpowiednich warunków ");
                    stan = l.zasadzenie(numer);
                } else {
                    System.out.println("Brak słońca");
                }
            } else if (stan == ZASADZENIE) {
                System.out.println("Nasionko " + numer + " może rosnąć ");
                stan = ROZROST;
            } else if (stan == ROZROST) {
                energia -= rand.nextInt(50);
                if (energia <= ILOSC_SLONCA) {
                    stan = ROZKWIT;
                } else try {
                    sleep(rand.nextInt(100));
                } catch (Exception e) {
                }
            } else if (stan == ROZKWIT) {
                System.out.println("Roślina " + numer + " potrzebuje słońca, pozostało jej " + energia + " energii");
                stan = l.kwiat();
                if (stan == ROZKWIT) {
                    energia -= rand.nextInt(50);
                    System.out.println("Dostępne słońce " + energia);
                    if (energia <= 0) stan = WIEDNIECIE;
                }
            } else if (stan == WIEDNIECIE) {
                System.out.println("Roślinka " + numer + " zwiędła :<");
                l.zmniejsz();
            }
        }
    }
}
