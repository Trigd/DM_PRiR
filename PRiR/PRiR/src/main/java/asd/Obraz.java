package asd;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class Obraz1 extends Thread {
    BufferedImage image;
    int width;
    int height;

    public Obraz1() {
        try {
            File input = new File("waterfall.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());
                    red = 255 - red;
                    green = 255 - green;
                    blue = 255 - blue;
                    Color newColor = new Color(red, green, blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File ouptut = new File("gray.jpg");
            ImageIO.write(image, "jpg", ouptut);
        } catch (Exception e) {
        }
    }
}
    class Obraz2 extends Thread {
        BufferedImage image;
        int width;
        int height;

        public Obraz2() {
            try {
                File input = new File("waterfall.jpg");
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();
                int x = 30, y = -10, z = 45;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(image.getRGB(j, i));
                        int red = (int) (c.getRed());
                        int green = (int) (c.getGreen());
                        int blue = (int) (c.getBlue());
                        if (red + x >= 0 && red + x <= 255) red = red + x;
                        if (green + y >= 0 && green + y <= 255) green = green + y;
                        if (blue + z >= 0 && blue + z <= 255) blue = blue + z;
                        Color newColor = new Color(red, green, blue);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                }
                File ouptut = new File("changed.jpg");
                ImageIO.write(image, "jpg", ouptut);
            } catch (Exception e) {
            }
        }
    }
    class Obraz {
    static public void main(String args[]) throws Exception
    {
        Obraz1 obr1 = new Obraz1();
        Obraz2 obr2 = new Obraz2();
        obr1.start();
        obr2.start();
        try {
            obr1.join();
            obr2.join();
        }
        catch (InterruptedException e) {}
    }
}