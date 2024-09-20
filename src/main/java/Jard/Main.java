package Jard;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jard using FXML.
 * Code is copied from the JavaFX tutorial provided on the 2103T website.
 */
public class Main extends Application {

    private Jard jard;

    @Override
    public void start(Stage stage) {
        try {
            jard = new Jard("./data/jard.txt");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            MainWindow controller = fxmlLoader.getController();
            String welcomeMessage = jard.getWelcomeMessage();
            controller.setJard(jard, welcomeMessage);

            stage.setTitle("Jard");

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
