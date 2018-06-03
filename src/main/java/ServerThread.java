package main.java;

import com.sun.javafx.css.CssError;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.omg.CORBA.INTERNAL;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileLockInterruptionException;

public class ServerThread extends Thread{

    private static final String DEFAULT_FILE = "index.html";
    private Socket connectionSocket;

    public ServerThread(Socket connectionSocket){
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {

        try {
            InputStream in = connectionSocket.getInputStream();
            OutputStream out = connectionSocket.getOutputStream();


            BufferedReader inClient = new BufferedReader(new InputStreamReader(in));
            DataOutputStream dos = new DataOutputStream(out);

            String requestMessage = inClient.readLine();
            //String[] parse = requestMessage.split(" ");

            String fileName = DEFAULT_FILE;
            File file = new File(fileName);

            FileInputStream infile = new FileInputStream(fileName);
            byte[] bytes = new byte[(int)file.length()];
            infile.read(bytes);

            dos.writeBytes("HTTP/1.1 200 OK \\r\\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + bytes.length + "\r\n");
            dos.writeBytes("\r\n");

            dos.write(bytes,0,bytes.length);
            dos.flush();
        }
        catch (Exception e){

        }

    }
}
