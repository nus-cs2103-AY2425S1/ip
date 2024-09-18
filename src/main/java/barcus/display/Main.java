package barcus.display;

import java.io.IOException;

import barcus.Barcus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Barcus barcus = new Barcus("./data/savedTasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            stage.setTitle("Barcus");
            Image image = new Image(this.getClass().getResourceAsStream("/images/barcusCat.png"));
            stage.getIcons().add(image);
            fxmlLoader.<MainWindow>getController().setBarcus(barcus); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
