package tina.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tina.Tina;



/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Tina tina = new Tina("./data/tina.txt");

    public static void exit() {
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Tina Chatbot");
            //ensure user unable to resize below min
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setTina(tina); //inject the Tina instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
