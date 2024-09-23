package mittens.ui.fx;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

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

            // Set up input stream for Mittens to receive user input
            PipedInputStream userInputStream = new PipedInputStream();
            PipedOutputStream userInputOutputStream = new PipedOutputStream(userInputStream);

            // Set up Mittens
            JavaFxUi ui = new JavaFxUi(userInputStream, fxmlLoader.<MainWindow>getController());

            Mittens mittens = new Mittens(ui, Mittens.DEFAULT_STORAGE_FILE_PATH);

            fxmlLoader.<MainWindow>getController().initialize(mittens, userInputOutputStream);

            // Run the application
            stage.setTitle("Mittens");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
