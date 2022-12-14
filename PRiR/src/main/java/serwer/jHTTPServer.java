package serwer;
import java.net.*;
import java.io.*;
import java.util.*;
public class jHTTPServer {
    private int port = 80;

    String getAnswer() {
        InetAddress adres;
        String name = "";
        String ip = "";
        try {
            adres = InetAddress.getLocalHost();
            name = adres.getHostName();
            ip = adres.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println(e);
        }
        String document = "<html>\r\n" +
                "<body><br>\r\n" +
                "<h1><center><font color=blue>Zadanie 6\r\n" +
                "</font></center></h1>\r\n" +
                "<h3>Serwer na watkach</h3><hr>\r\n" +
                "Data: <b>" + new Date() + "</b><br>\r\n" +
                "Nazwa hosta: <b>" + name + "</b><br>\r\n" +
                "IP hosta: <b>" + ip + "</b><br>\r\n" +
                "<hr>\r\n" +
                "</body>\r\n" +
                "</html>\r\n";
        String header = "HTTP/1.1 200 OK\r\n" +
                "Server: jHTTPServer ver 1.1\r\n" +
                "Last-Modified: Fri, 28 Jul 2000 07:58:55 GMT\r\n" +
                "Content-Length: " + document.length() + "\r\n" +
                "Connection: close\r\n" +
                "Content-Type: text/html";
        return header + "\r\n\r\n" + document;
    }

    public jHTTPServer(int port) {
        this.port = port;
        Socket socket = null;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                socket = server.accept();
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
                if (socket != null) socket.close();
            }
        } catch (IOException e) {
            System.out.println("Blad otwarcia");
        }
    }

    public static void main(String[] args) {
        new jHTTPServer(80);
    }
}