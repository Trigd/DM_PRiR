//package asd;
//import java.util.concurrent.Semaphore ;
//import java.util.Scanner;
//public class Filozof extends Thread {
//    static Scanner s1 = new Scanner(System.in);
//    static String numer = s1.nextLine();
//    static final int MAX = Integer.parseInt(numer);
//    static Semaphore [] widelec = new Semaphore [MAX] ;
//    int mojNum;
//    public Filozof ( int nr ) {
//        mojNum=nr ;
//    }
//    public void run ( ) {
//        while ( true ) {
//// myslenie
//            System.out.println ( "Mysle ¦ " + mojNum) ;
//            try {
//                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
//            } catch ( InterruptedException e ) {
//            }
//            widelec [mojNum].acquireUninterruptibly ( ) ; //przechwycenie L widelca
//            widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ; //przechwycenie P widelca
//// jedzenie
//            System.out.println ( "Zaczyna jesc "+mojNum) ;
//            try {
//                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
//            } catch ( InterruptedException e ) {
//            }
//            System.out.println ( "Konczy jesc "+mojNum) ;
//            widelec [mojNum].release ( ) ; //zwolnienie L widelca
//            widelec [ (mojNum+1)%MAX].release ( ) ; //zwolnienie P widelca
//        }
//    }
//    public static void main ( String [] args ) {
//        for ( int i =0; i<MAX; i++) {
//            widelec [ i ]=new Semaphore ( 1 ) ;
//        }
//        for ( int i =0; i<MAX; i++) {
//            new Filozof(i).start();
//        }
//    }
//}

package asd;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.Semaphore ;

class Filozof {
    static Scanner s1 = new Scanner(System.in);
    static String numer = s1.nextLine();
    static final int MAX = Integer.parseInt(numer);
    static Semaphore[] widelec = new Semaphore[MAX];
    int mojNum;
    Random losuj;

    public class Filozof1 extends Thread {
        static Semaphore[] widelec = new Semaphore[MAX];
        int mojNum;

        public Filozof1(int nr) {
            mojNum = nr;
        }

        public void run() {
            while (true) {
// myslenie
                System.out.println("Mysle ¦ " + mojNum);
                try {
                    Thread.sleep((long) (7000 * Math.random()));
                } catch (InterruptedException e) {
                }
                widelec[mojNum].acquireUninterruptibly(); //przechwycenie L widelca
                widelec[(mojNum + 1) % MAX].acquireUninterruptibly(); //przechwycenie P widelca
// jedzenie
                System.out.println("Zaczyna jesc " + mojNum);
                try {
                    Thread.sleep((long) (5000 * Math.random()));
                } catch (InterruptedException e) {
                }
                System.out.println("Konczy jesc " + mojNum);
                widelec[mojNum].release(); //zwolnienie L widelca
                widelec[(mojNum + 1) % MAX].release(); //zwolnienie P widelca
            }
        }
    }

    class Filozof2 extends Thread {
        static Semaphore[] widelec = new Semaphore[MAX];
        int mojNum;

        public Filozof2(int nr) {
            mojNum = nr;
        }

        public void run() {
            while (true) {
// myslenie
                System.out.println("Mysle ¦ " + mojNum);
                try {
                    Thread.sleep((long) (5000 * Math.random()));
                } catch (InterruptedException e) {
                }
                if (mojNum == 0) {
                    widelec[(mojNum + 1) % MAX].acquireUninterruptibly();
                    widelec[mojNum].acquireUninterruptibly();
                } else {
                    widelec[mojNum].acquireUninterruptibly();
                    widelec[(mojNum + 1) % MAX].acquireUninterruptibly();
                }
// jedzenie
                System.out.println("Zaczyna jesc " + mojNum);
                try {
                    Thread.sleep((long) (3000 * Math.random()));
                } catch (InterruptedException e) {
                }
                System.out.println("Konczy jesc " + mojNum);
                widelec[mojNum].release();
                widelec[(mojNum + 1) % MAX].release();
            }
        }
    }

    class Filozof3 extends Thread {
        static Semaphore[] widelec = new Semaphore[MAX];
        int mojNum;
        Random losuj;

        public Filozof3(int nr) {
            mojNum = nr;
            losuj = new Random(mojNum);
        }

        public void run() {
            while (true) {
// myslenie
                System.out.println("Mysle ¦ " + mojNum);
                try {
                    Thread.sleep((long) (5000 * Math.random()));
                } catch (InterruptedException e) {
                }
                int strona = losuj.nextInt(2);
                boolean podnioslDwaWidelce = false;
                do {
                    if (strona == 0) {
                        widelec[mojNum].acquireUninterruptibly();
                        if (!(widelec[(mojNum + 1) % MAX].tryAcquire())) {
                            widelec[mojNum].release();
                        } else {
                            podnioslDwaWidelce = true;
                        }
                    } else {
                        widelec[(mojNum + 1) % MAX].acquireUninterruptibly();
                        if (!(widelec[mojNum].tryAcquire())) {
                            widelec[(mojNum + 1) % MAX].release();
                        } else {
                            podnioslDwaWidelce = true;
                        }
                    }
                } while (podnioslDwaWidelce == false);
                System.out.println("Zaczyna jesc " + mojNum);
                try {
                    Thread.sleep((long) (3000 * Math.random()));
                } catch (InterruptedException e) {
                }
                System.out.println("Konczy jesc " + mojNum);
                widelec[mojNum].release();
                widelec[(mojNum + 1) % MAX].release();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Podaj liczbę myśliceli:");
        Scanner s2 = new Scanner(System.in);
        System.out.println("Podaj opcję walki o jedzenie:");
        String opcja = s2.nextLine();

        //Filozof1 fil1 = new Filozof1();
        for (int i = 0; i < MAX; i++) {
            widelec[i] = new Semaphore(1);
        }
        for (int i = 0; i < MAX; i++) {
            switch (opcja) {
//                case "1":
//                    fil1.start();
//                case "2":
//                    new Filozof2(i).start();
//                case "3":
//                    new Filozof3(i).start();
            }
        }
    }
}