package serwer;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.net.*;
import java.io.*;
import java.util.*;
class jHTTPSMulti extends Thread{
    BufferedImage image;
    private Socket socket = null;
    String getAnswer() {
        InetAddress adres;
        String name = "";
        String ip = "";
        try {
            //File input = new File("waterfall.jpg");
            //image = ImageIO.read(input);
            adres = InetAddress.getLocalHost();
            name = adres.getHostName();
            ip = adres.getHostAddress();
        }
        catch (Exception ex) { System.err.println(ex); }
        //try{
        //File input = new File("kitku.jpg");
        //image = ImageIO.read(input);}
        //catch(Exception e) {}
        String document = "<html>\r\n" +
                "<body bgcolor=pink><br>\r\n" +
                "<title>Zadanko nr 6</title>" +
                "<h1><center><font color=blue size=20px>Zadanie 6\r\n" +
                "<hr color=blue width=500>" +
                "</font></center></h1>\r\n" +
                "<h3><i><font color=green size=15px>Serwer</font></i> na watkach</h3><hr color=red>\r\n" +
                "Data: <b>" + new Date() + "</b><br>\r\n" +
                "Nazwa hosta: <b>" + name + "</b><br>\r\n" +
                "IP hosta: <b>" + ip + "</b><br>\r\n" +
                "<hr color=black>\r\n" +
                "<img src='kitku.jpg' width=250></img>" +
                //ImageIO.read(input) +
                "</body>\r\n" +
                "</html>\r\n";
        String header = "HTTP/1.1 200 OK\r\n" +
                image +
                "Server: jHTTPServer ver 1.1\r\n" +
                "Last-Modified: Fri, 28 Jul 2000 07:58:55 GMT\r\n" +
                "Content-Length: " + document.length() + "\r\n" +
                "Connection: close\r\n" +
                "Content-Type: text/html; charset=iso-8859-2";
        return header + "\r\n\r\n" + document;
    }
    public jHTTPSMulti(Socket socket){
        System.out.println("Nowy obiekt jHTTPSMulti...");
        this.socket = socket;
        start();
    }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            System.out.println("----------------Pierwsza linia zapytania ----------------");
            System.out.println(in.readLine());
            System.out.println("----------------Wysylam odpowiedz -----------------------");
            System.out.println(getAnswer());
            System.out.println("----------------Koniec odpowiedzi -----------------------");
            out.println(getAnswer());
            out.flush();
        } catch (IOException e) {
            System.out.println("Blad IO danych!");
        }
        finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Blad zamkniecia gniazda!");
            }
        } // finally
    }
}
