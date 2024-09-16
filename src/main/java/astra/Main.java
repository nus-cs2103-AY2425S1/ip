package astra;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Astra using FXML.
 */
public class Main extends Application {

    private final Astra astra = new Astra("./data");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            stage.setTitle("Astra");
            Image image = new Image(this.getClass().getResourceAsStream("/images/Astra.png"));
            stage.getIcons().add(image);

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAstra(astra);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
