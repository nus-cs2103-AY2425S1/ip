package rapgod;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rapgod.bot.RapGod;
import rapgod.ui.MainWindow;

/**
 * A GUI for rapgod.Duke using FXML.
 */
public class Main extends Application {

    private RapGod rapGod = new RapGod();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDuke(rapGod);  // inject the rapgod.Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}