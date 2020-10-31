package sample;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.setTitle("Login Window");
            primaryStage.setScene(new Scene(root, 600, 299));
            Platform.isImplicitExit();
            primaryStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}



