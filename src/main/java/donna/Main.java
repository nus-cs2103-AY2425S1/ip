package donna;

import donna.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GUI for Donna using FXML
 */
public class Main extends Application {
    private Donna donna = new Donna();
    private Ui ui = donna.getDonnaUi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDonna(donna, ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
