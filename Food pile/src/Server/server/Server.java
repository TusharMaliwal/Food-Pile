package Server.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;

public class Server {
    public static void main(String[] args) throws  FileNotFoundException , SQLException, ClassNotFoundException {
        ServerSocket serverSocket;
        Socket socket;
        Connection connection = DBConnection.connect();
        System.out.println("Server Started");
        try {
            serverSocket = new ServerSocket(5400);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
            socket = serverSocket.accept();
            System.out.println("Connected To client");
            Thread t = new Thread(new HandleClient(socket,connection));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}