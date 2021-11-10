package com.luxoft.serverresponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //static ServerSocket variable
    private static ServerSocket server;

    //socket server port on which it will listen
    private static int port = 11111;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);

        while (true) {

            Socket socket = server.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String message = (String) ois.readObject();
            System.out.println(message);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject("echo: " + message);

            ois.close();
            oos.close();
            socket.close();

            if (message.equalsIgnoreCase("exit")) {
                break;
            }
        }

        server.close();
    }
}
