package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_loginerror {
    public Button retryButton;
    public Label headLabel1;
    public Label headLabel2;

    public void retryListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) retryButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
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
