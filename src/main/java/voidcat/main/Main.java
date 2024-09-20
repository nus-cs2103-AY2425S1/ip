package voidcat.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for VoidCat using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data/void.txt";
    private Image voidCatImage = new Image(this.getClass().getResourceAsStream("/images/VoidCat.png"));
    private VoidCat voidCat = new VoidCat(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("VoidCat");
            stage.getIcons().add(voidCatImage);
            fxmlLoader.<MainWindow>getController().setVoidCat(voidCat); // injects the VoidCat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

