package serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class jHTTPApp {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        try {
            while (true) {
                Socket socket = server.accept();
                new jHTTPSMulti(socket);
            } // while
        } // try
        finally { server.close();}
    } // main
}