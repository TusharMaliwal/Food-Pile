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


public class HandleClient implements Runnable {

    private final Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final Connection connection;
    private boolean isLoggedIn;

    private String username;

    public HandleClient(Socket socket,Connection connection){
        this.socket = socket;
        this.connection = connection;
        this.isLoggedIn = false;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (!isLoggedIn){
            try {
                Request req = (Request) this.getRequest();
                if(req instanceof LoginRequest)
                {
                    LoginResponse res = UserRequestHandler.login((LoginRequest) req, connection);
                    this.sendReponse(res);
                    if(res.getCode() == ResponseCode.SUCCESS){
                        isLoggedIn = true;
                        this.username = res.getUsername();
                        System.out.println(res+"Just logged in.");
                        break;
                    }
                }
                else if (req instanceof SignupRequest)
                {
                    SignupResponse res = UserRequestHandler.signup((SignupRequest) req,connection);
                    this.sendReponse(res);
                    if(res.getCode() == ResponseCode.SUCCESS){
                        System.out.println(res+"Just signed up.");
                    }
                }
                else
                {
                    continue;
                }
            } catch (Exception e) {
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

        while (true){
            try {
                Request req = (Request) this.getRequest();
                Response res = RequestHandler.getResponse(req,connection);
                this.sendReponse(res);
                System.out.println(res);
            }
            catch (Exception e){
                System.out.println("User is signing off.");
                return;
            }
        }
    }
    public void sendReponse(Response res) throws IOException {
        objectOutputStream.writeObject(res);
        objectOutputStream.flush();
    }
    public Object getRequest() throws IOException, ClassNotFoundException {
        Object req = objectInputStream.readObject();
        return req;
    }
}
