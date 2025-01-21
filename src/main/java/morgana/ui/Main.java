package morgana.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import morgana.Morgana;

/**
 * A GUI for Morgana using FXML.
 */
public class Main extends Application {
    private final Morgana morgana = new Morgana();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        fxmlLoader.setControllerFactory(controller -> new MainWindow(morgana));
        stage.setTitle("MorganaPro");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }
}
