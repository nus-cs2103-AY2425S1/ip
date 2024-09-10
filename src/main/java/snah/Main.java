package snah;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snah.controls.MainWindow;

/**
 * Main class to create the main window for Snah chatbot
 */
public class Main extends Application {

    private Snah snah = new Snah();

    public Main() {
    }

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSnah(snah); // inject the Snah instance
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
