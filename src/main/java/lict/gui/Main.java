
package lict.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lict.Lict;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lict lict = new Lict();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLict(lict); // inject the Lict instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
