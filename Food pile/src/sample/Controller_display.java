package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;


public class Controller_display {
    public Label headLabel;
    public Button priceButton;
    public Button quantityButton;

    public void priceListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException{
        Parent root = null;
        Stage primaryStage = (Stage) priceButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplaybyPrice.fxml"));
        try {

            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }
    public void quantityListener(ActionEvent actionEvent) throws IOException, ClassNotFoundException{
        Parent root = null;
        Stage primaryStage = (Stage) quantityButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplaybyQuantity.fxml"));
        try {

            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

}
