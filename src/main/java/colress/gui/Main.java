package colress.gui;

import java.io.IOException;

import colress.Colress;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Colress using FXML.
 */
public class Main extends Application {

    private final Colress colress = new Colress("task.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Colress");
            stage.setScene(scene);
            stage.setMinHeight(300);
            stage.setMinWidth(450);

            Image image = new Image("/images/Colress.png");
            stage.getIcons().add(image);
            fxmlLoader.<MainWindow>getController().setColress(colress);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
