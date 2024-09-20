package sadcat.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sadcat.SadCat;

/**
 * Main entry point for the application into the GUI
 *
 */
public class Main extends Application {
    private SadCat sadcat = new SadCat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSadCat(sadcat);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().sendIntro();
            stage.setTitle("SadCat");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
