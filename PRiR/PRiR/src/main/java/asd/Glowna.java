package asd;

public class Glowna{
    static int ilosc_roslin = 100;
    static int ilosc_miejsca = 10;
    static Las las;
    public Glowna(){
    }
    public static void main(String[] args) {
        las =new Las(ilosc_miejsca, ilosc_roslin);
        for(int i = 0; i < ilosc_roslin; i++)
            new Roślina(i,200, las).start();
    }
}
