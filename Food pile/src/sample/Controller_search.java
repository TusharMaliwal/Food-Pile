package sample;

import common.requests.Funct.SearchCategory;
import common.requests.Funct.SearchName;
import common.requests.Request;
import common.responses.Funct.SearchCategoryResponse;
import common.responses.Funct.SearchNameResponse;
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

public class Controller_search {
    public Label headLabel;
    public TextField nameTextField;
    public TextField categoryTextField;
    public TextField usernameTextField;
    public Button nameButton;
    public Button categoryButton;



    public void nameListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException{
        String name = this.nameTextField.getText();
        String username = this.usernameTextField.getText();
        Socket socket = new Socket("localhost", 5470);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Request req = new SearchName(username, name);
        objectOutputStream.writeObject(req);
        objectOutputStream.flush();
        Response res = (SearchNameResponse) objectInputStream.readObject();
        if (res.getCode() == ResponseCode.SUCCESS) {
            Parent root = null;
            Stage primaryStage = (Stage) nameButton.getScene().getWindow();
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
            Stage primaryStage = (Stage) nameButton.getScene().getWindow();
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
    public void categoryListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException{
        String category = this.categoryTextField.getText();
        String username = this.usernameTextField.getText();
        Socket socket = new Socket("localhost", 5470);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Request req = new SearchCategory(username, category);
        objectOutputStream.writeObject(req);
        objectOutputStream.flush();
        Response res = (SearchCategoryResponse) objectInputStream.readObject();
        if (res.getCode() == ResponseCode.SUCCESS) {
            Parent root = null;
            Stage primaryStage = (Stage) categoryButton.getScene().getWindow();
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
            Stage primaryStage = (Stage) categoryButton.getScene().getWindow();
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
