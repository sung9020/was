package main.java;
import java.net.*;
import java.lang.*;

public class webServer {
    public static void main(String[] args) throws Exception {
        ServerSocket litsenSocket = new ServerSocket(8080);

        Socket connectionSocket;

        while((connectionSocket = litsenSocket.accept()) != null){
            ServerThread serverThread = new ServerThread(connectionSocket);
            serverThread.start();
        }
    }
}
