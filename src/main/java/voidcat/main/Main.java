package voidcat.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for VoidCat using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "./data/void.txt";
    private VoidCat voidCat = new VoidCat(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("VoidCat");
            fxmlLoader.<MainWindow>getController().setVoidCat(voidCat);  // inject the VoidCat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

