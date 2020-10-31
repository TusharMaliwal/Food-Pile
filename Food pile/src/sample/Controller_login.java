package sample;

import common.responses.ResponseCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import common.responses.Response;
import common.requests.Request;
import common.requests.auth.LoginRequest;
import common.responses.auth.LoginResponse;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Controller_login {
    public Label headLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label instructionLabel;
    public TextField usernameTextField;
    public PasswordField passwordField;
    public Button LoginButton;
    public Button SignupButton;



    public void loginListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String username = this.usernameTextField.getText();
        String password = this.passwordField.getText();
        Socket socket = new Socket("localhost", 5470);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Request req = new LoginRequest(username, password);
        objectOutputStream.writeObject(req);
        objectOutputStream.flush();
        Response res =  (LoginResponse) objectInputStream.readObject();
        if (res.getCode() == ResponseCode.SUCCESS) {
            Parent root = null;
            Stage primaryStage = (Stage) LoginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            try {

                root = fxmlLoader.load();
            }catch(IOException e){
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();



        }
        else{
            Parent root = null;
            Stage primaryStage = (Stage) LoginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginError.fxml"));
            try {

                root = fxmlLoader.load();
            }catch(IOException e){
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();




        }
    }
    public void signupListener(ActionEvent actionEvent) throws IOException{

        Parent root = null;
        Stage primaryStage = (Stage) SignupButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }
}
