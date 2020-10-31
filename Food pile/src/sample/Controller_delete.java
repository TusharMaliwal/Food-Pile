package sample;

import common.requests.Funct.DeleteItem;
import common.requests.Request;
import common.responses.Funct.DeleteItemResponse;
import common.responses.Response;
import common.responses.ResponseCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_delete {
    public Label headLabel;
    public Label usernameLabel;
    public Label productidLabel;
    public Button submitButton;
    public TextField usernameTextField;
    public TextField productidTextField;

    public void submitListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException{
        Socket socket = new Socket("localhost", 5470);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        String username = this.usernameTextField.getText();
        String productID = this.productidTextField.getText();
        Request req = new DeleteItem(username, productID);
        objectOutputStream.writeObject(req);
        objectOutputStream.flush();
        Response res = (DeleteItemResponse) objectInputStream.readObject();
        if (res.getCode() == ResponseCode.SUCCESS) {
            Parent root = null;
            Stage primaryStage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("success.fxml"));
            try {

                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();


        } else {
            Parent root = null;
            Stage primaryStage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            try {

                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        }
    }

}
