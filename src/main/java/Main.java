import java.io.IOException;

import hana.Hana;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Hana hana = new Hana();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Hana ChatBot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHana(hana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
