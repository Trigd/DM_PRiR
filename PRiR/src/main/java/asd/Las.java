package asd;

public class Las {
    static int LAS=1;
    static int ZASADZENIE=2;
    static int ROZROST=3;
    static int ROZKWIT=4;
    static int WIEDNIECIE=5;
    int ilosc_miejsca;
    int ilosc_zajetych;
    int ilosc_roslin;
    Las(int ilosc_miejsca, int ilosc_roslin){
        this.ilosc_miejsca =ilosc_miejsca;
        this.ilosc_roslin =ilosc_roslin;
        this.ilosc_zajetych=0;
    }
    synchronized int zasadzenie(int numer){
        ilosc_zajetych--;
        System.out.println("Roślinka " + numer + " znalazła miejsce ");
        return ZASADZENIE;
    }
    synchronized int kwiat(){
        try{
            Thread.currentThread().sleep(500);
        }
        catch(Exception ie){
        }
        if(ilosc_zajetych < ilosc_miejsca){
            ilosc_zajetych++;
            System.out.println("Roślinka chce wzbić się po promienie na miejscu "+ilosc_zajetych);
            return LAS;
        }
        else
        {return ROZKWIT;}
    }
    synchronized void zmniejsz(){
        ilosc_roslin--;
        System.out.println("Roślinka nie przeżyła");
        if(ilosc_roslin == ilosc_miejsca) System.out.println("Ilość roślin równa ilości miejsca ______________");
    }
}
