package lumina.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lumina.main.Lumina;

/** GUI Main class */
public class Main extends Application {

    private Lumina lumina = new Lumina();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class
                    .getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Lumina");
            // inject the Lumina instance
            fxmlLoader.<MainWindow>getController().setLumina(lumina);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
