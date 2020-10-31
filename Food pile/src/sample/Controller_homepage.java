package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_homepage {
    public Button addButton;
    public Button displayButton;
    public Button searchButton;
    public Button logoutButton;
    public Button updateButton;
    public Button deleteButton;
    public Label additemsLabel;
    public Label searchLabel;
    public Label updateLabel;
    public Label deleteLabel;
    public Label displayLabel;
    public Label headLabel;

    public void logoutListener(ActionEvent actionEvent) {

        Main.logout();
    }

    public void addListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) addButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("additem.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }

    public void displayListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) displayButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Display.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }

    public void searchListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) searchButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }



    public void deleteListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) deleteButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }

    public void updateListener(ActionEvent actionEvent) {
        Parent root = null;
        Stage primaryStage = (Stage) updateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateitem.fxml"));
        try {

            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }


}
