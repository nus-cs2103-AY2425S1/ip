package meerkatpack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for meerkatpack.Meerkat using FXML.
 */
public class Main extends Application {

    private Meerkat meerkat = new Meerkat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setMeerkat(meerkat);  // inject the Meerkat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}