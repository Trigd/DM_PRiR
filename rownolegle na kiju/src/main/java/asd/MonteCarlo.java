package asd;
class MC {
    public float getRandomNumber(int min, int max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
class Watek extends Thread {
    public void run() {
        MC mc = new MC();
        for (int i = 0; i < 10; i++) {
            mc.getRandomNumber(0, 1);
        }
    }
}
class MonteCarlo {
    public void main(String args[]) {
        Watek w1 = new Watek();
        w1.start();
        Watek w2 = new Watek();
        w2.start();
        Watek w3 = new Watek();
        w3.start();
        Watek w4 = new Watek();
        w4.start();
        try {
            w1.join();
            w2.join();
            w3.join();
            w4.join();
            System.out.println("Watki skonczyly prace");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
