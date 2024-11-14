package guy.gui;

import java.io.IOException;

import guy.ThatOneGuy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for application into the GUI.
 */
public class Main extends Application {
    private ThatOneGuy guy = new ThatOneGuy();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/components/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGuy(guy);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().sendIntro();
            stage.setTitle("ThatOneGuy");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
