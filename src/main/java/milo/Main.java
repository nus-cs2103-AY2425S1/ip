package milo;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * A GUI for Milo using FXML.
 */
public class Main extends Application {

    private final Milo milo = new Milo();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();  // Create an instance of MainWindow, which will load the FXML
        mainWindow.setMilo(milo);  // Inject the Milo instance
        Scene scene = new Scene(mainWindow);  // Use the MainWindow instance as the root
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Milo");
        stage.show();
    }
}
