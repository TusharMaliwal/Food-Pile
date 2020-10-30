package Server.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.net.ServerSocket;
import java.sql.Connection;

public class Server {
    public static void main(String[] args) throws  FileNotFoundException , SQLException, ClassNotFoundException {
        ServerSocket serverSocket;
        Socket socket;
        Connection connection = DBConnection.connect();
        System.out.println("Server Started");
        try {
            serverSocket = new ServerSocket(5470);
        } catch (IOException e) {
            System.out.println("Port already in use");
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
            socket = serverSocket.accept();
            System.out.println("Connected To client");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Thread t = new Thread(new HandleClient(socket,connection,objectInputStream,objectOutputStream));
            t.start();
        } catch (IOException e) {
            System.out.println("Client suddenly stopped");
        }
    }
}
}