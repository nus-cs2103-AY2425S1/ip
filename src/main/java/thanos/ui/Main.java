package thanos.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thanos.Thanos;
import thanos.ui.components.MainWindow;

/**
 * The main entry point of the JavaFX application.
 * <p>
 * The {@code Main} class extends {@code Application} and is responsible for launching the JavaFX application,
 * loading the main window layout from an FXML file, applying CSS styles, and initializing the application state.
 * </p>
 */
public class Main extends Application {
    private final Thanos thanos = new Thanos("data.txt");

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage the primary stage for this application, onto which the scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Objects.requireNonNull(
                    this.getClass().getResource("/css/application.css")).toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Thanos");
            fxmlLoader.<MainWindow>getController().setThanos(thanos);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
