package sample;

import javafx.application.Application;
import javafx.application.Platform;

public class Main {

    public static void main(String[] args) {

        Application.launch(Start.class, args);
    }

    public static void logout() {
        Platform.exit();

    }
}
