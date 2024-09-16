package barney.gui;

import java.io.IOException;

import barney.Barney;
import barney.gui.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Barney barney;

    @Override
    public void init() {
        Parameters params = getParameters();
        String saveFilePath = params.getRaw().get(0);
        barney = new Barney(saveFilePath);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Barney");
            fxmlLoader.<MainWindow>getController().setBarney(barney);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
