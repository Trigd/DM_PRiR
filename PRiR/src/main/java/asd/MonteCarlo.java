package asd;
class MC {
    public float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
class Watek extends Thread {
    float Po = 0;
    float Pk = 0;
    float wys;
    float szer;
    float R = 4;
    public void run() {
        MC mc = new MC();
        for (int i = 0; i < 10000; i++) {
            wys = mc.getRandomNumber(-R, R);
            szer = mc.getRandomNumber(-R, R);
            if (wys * wys + szer * szer <= R * R) {
                Po += 1;
            }
            else Pk += 1;
        }
        System.out.println(Po/Pk);
    }
}
class MonteCarlo {
    public static void main(String args[]) {
        Watek w1 = new Watek();
        w1.start();
        Watek w2 = new Watek();
        w2.start();
        Watek w3 = new Watek();
        w3.start();
        Watek w4 = new Watek();
        w4.start();
        System.out.println("Pole koła według algorytmu Monte Carlo:");
        try {
            w1.join();
            w2.join();
            w3.join();
            w4.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
