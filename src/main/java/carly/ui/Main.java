package carly.ui;

import java.io.IOException;

import carly.Carly;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class is the entry point for the JavaFX application.
 * It sets up the main user interface window using an FXML file.
 */
public class Main extends Application {
    private final Carly carly = new Carly();

    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Carly Chatbot");

            Image image = new Image(Main.class.getResourceAsStream("/images/penguinicon.jpg"));
            stage.getIcons().add(image);

            fxmlLoader.<MainWindow>getController().setCarly(carly);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
