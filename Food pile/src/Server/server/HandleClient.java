package Server.server;

import Server.requestHandler.RequestHandler;
import Server.requestHandler.UserRequestHandler;
import common.requests.Request;
import common.requests.auth.LoginRequest;
import common.requests.auth.SignupRequest;
import common.responses.Response;
import common.responses.ResponseCode;
import common.responses.auth.LoginResponse;
import common.responses.auth.SignupResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;


public class HandleClient implements Runnable {

    private final Socket socket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    private final Connection connection;
    private boolean isLoggedIn;

    private String username;

    public HandleClient
            (Socket socket,Connection connection,ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream){
        this.socket = socket;
        this.connection = connection;
        this.objectInputStream=objectInputStream;
        this.objectOutputStream=objectOutputStream;
        this.isLoggedIn = false;
    }
    @Override
    public void run() {

        while (!isLoggedIn){
            try {
                Request req = (Request) objectInputStream.readObject();
                if(req instanceof LoginRequest)
                {
                    LoginResponse res = UserRequestHandler.login((LoginRequest) req, connection);
                    this.sendResponse(res);
                    if(res.getCode() == ResponseCode.SUCCESS){
                        isLoggedIn = true;
                        this.username = res.getUsername();
                        System.out.println(res+"Just logged in.");
                        break;
                    }
                    System.out.println(res);
                }
                else if (req instanceof SignupRequest)
                {
                    SignupResponse res = UserRequestHandler.signup((SignupRequest) req,connection);
                    this.sendResponse(res);
                    if(res.getCode() == ResponseCode.SUCCESS){
                        isLoggedIn = true;
                        System.out.println(res+"Just signed up.");
                        break;
                    }
                    System.out.println(res);
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("User is closing application.");
                try {
                    this.objectInputStream.close();
                    this.objectOutputStream.close();
                    return;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        while(true){
            try{
                Request req = (Request) objectInputStream.readObject();
                Response res = RequestHandler.getResponse(req,connection);
                this.sendResponse(res);
                System.out.println(res);
            }catch(SQLException e){
                System.out.println("User is logging out");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("User is Closing application");
                try {
                    this.objectInputStream.close();
                    this.objectOutputStream.close();
                    return;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
    public void sendResponse(Response res) {
        try {
            objectOutputStream.writeObject(res);
            objectOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Error in output stream");
        }
    }

}