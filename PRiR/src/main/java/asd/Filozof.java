package asd;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    public static Scanner s1 = new Scanner(System.in);
    static int MAX = Integer.parseInt(s1.nextLine());
    static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;
    int wariant;
    Random losuj ;
    public Filozof ( int nr, int opcja ) {
        mojNum = nr;
        wariant = opcja;
        losuj = new Random(mojNum) ;
    }
    public void run ( ) {
        while (true && wariant == 1 && MAX <= 100 && MAX >= 2) {
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
        while (true && wariant == 2 && MAX <= 100 && MAX >= 2) {
// myslenie
        System.out.println ( "Mysle ¦ " + mojNum) ;
        try {
            Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
        } catch ( InterruptedException e ) {
        }
        if (mojNum == 0) {
            widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
            widelec [mojNum].acquireUninterruptibly ( ) ;
        } else {
            widelec [mojNum].acquireUninterruptibly ( ) ;
            widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
        }
// jedzenie
        System.out.println ( "Zaczyna jesc "+mojNum) ;
        try {
            Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
        } catch ( InterruptedException e ) {
        }
        System.out.println ( "Konczy jesc "+mojNum) ;
        widelec [mojNum].release ( ) ;
        widelec [ (mojNum+1)%MAX].release ( ) ;
    }

        while (true && wariant == 3 && MAX <= 100 && MAX >= 2) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            int strona = losuj.nextInt ( 2 ) ;
            boolean podnioslDwaWidelce = false ;
            do {
                if ( strona == 0) {
                    widelec [mojNum].acquireUninterruptibly ( ) ;
                    if( ! ( widelec [ (mojNum+1)%MAX].tryAcquire ( ) ) ) {
                        widelec[mojNum].release ( ) ;
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                } else {
                    widelec[(mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                    if ( ! (widelec[mojNum].tryAcquire ( ) ) ) {
                        widelec[(mojNum+1)%MAX].release ( ) ;
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                }
            } while ( podnioslDwaWidelce == false ) ;
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            widelec [mojNum].release ( ) ;
            widelec [ (mojNum+1)%MAX].release ( ) ;
        }
    }

    public static void main ( String [] args ) {
        Scanner s2 = new Scanner(System.in);
        System.out.println("Podaj opcję walki o jedzenie:");
        int opcja = Integer.parseInt(s2.nextLine());
        for ( int i =0; i<MAX; i++) {
            widelec [ i ]=new Semaphore ( 1 ) ;
        }
        for ( int i =0; i<MAX; i++) {
            new Filozof(i, opcja).start();
        }
    }
}



