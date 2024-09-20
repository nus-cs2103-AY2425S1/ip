package johncena.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import johncena.JohnCena;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private JohnCena johnCena = new JohnCena();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJohnCena(johnCena);  // inject the JohnCena instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
