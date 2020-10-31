package sample;

import common.requests.Funct.DisplayPrice;
import common.requests.Request;
import common.responses.Funct.DisplayPriceResponse;
import common.responses.Response;
import common.responses.ResponseCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller_displayprice {
    public Label headLabel;
    public Button backButton;
    public Button logoutButton;
    public Button submitButton;
    public TextArea displayArea;
    public TextField usernameTextField;

 ;
    public void submitListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException{
        String username= this.usernameTextField.getText();
        Socket socket = new Socket("localhost", 5470);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Request req = new DisplayPrice(username);
        objectOutputStream.writeObject(req);
        objectOutputStream.flush();
        Response res = (DisplayPriceResponse) objectInputStream.readObject();
        if (res.getCode() == ResponseCode.SUCCESS) {
            Parent root = null;
            Stage primaryStage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("success.fxml"));
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
            Stage primaryStage = (Stage) submitButton.getScene().getWindow();
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

    public void backListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }
    public void logoutListener(ActionEvent actionEvent) {
        Main.logout();
    }
}
