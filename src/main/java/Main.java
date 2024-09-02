import java.io.IOException;

import hana.Hana;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Hana using FXML.
 */
public class Main extends Application {

    private Hana hana = new Hana();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Hana ChatBot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();
            fxmlLoader.setRoot(mainWindow);
            fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            mainWindow.setStage(stage);
            mainWindow.setHana(hana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
