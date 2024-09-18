package botty.ui;

import java.io.IOException;

import botty.Botty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Botty botty = new Botty();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Botty the bot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBotty(botty);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
