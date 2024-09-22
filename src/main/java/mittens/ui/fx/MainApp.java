package mittens.ui.fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mittens.Mittens;
import mittens.ui.JavaFxUi;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set up Mittens
            JavaFxUi ui = new JavaFxUi(fxmlLoader.<MainWindow>getController());

            Mittens mittens = new Mittens(ui, Mittens.DEFAULT_STORAGE_FILE_PATH);

            fxmlLoader.<MainWindow>getController().initialize(mittens);

            // Run the application
            stage.setTitle("Mittens");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
