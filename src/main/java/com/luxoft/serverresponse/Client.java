package com.luxoft.serverresponse;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        BufferedReader bufferedReader = null;

        socket = new Socket(host.getHostName(), 11111);

        while (true) {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            oos = new ObjectOutputStream(socket.getOutputStream());

            String s1 = bufferedReader.readLine();
            if (s1.equals("exit")) {
                oos.writeObject("exit");
                break;
            }
            oos.writeObject(s1);

            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println(message);
        }
        ois.close();
        oos.close();
    }
}
