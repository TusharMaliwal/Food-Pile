package sample;

import common.requests.Request;
import common.requests.auth.SignupRequest;
import common.responses.ResponseCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import common.responses.auth.SignupResponse;
import common.responses.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_signup {
    public Label headLabel;
    public Label firstnameLabel;
    public Label lastnameLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public TextField firstnameTextField;
    public TextField lastnameTextField;
    public TextField usernameTextField;
    public PasswordField passwordField;
    public Button signupButton;


    public void signupListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 5470);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        String username = this.usernameTextField.getText();
        String password = this.passwordField.getText();
        String fname = this.firstnameTextField.getText();
        String lname = this.lastnameTextField.getText();
        Request req = new SignupRequest(fname, lname, username, password);
        objectOutputStream.writeObject(req);
        objectOutputStream.flush();
        Response res =  (SignupResponse) objectInputStream.readObject();
        if (res.getCode() == ResponseCode.SUCCESS) {
            Parent root = null;
            Stage primaryStage = (Stage) signupButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
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
            Stage primaryStage = (Stage) signupButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            try {

                root = fxmlLoader.load();
            }catch(IOException e){
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();




        }

    }
}
