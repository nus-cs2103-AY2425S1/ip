import java.io.IOException;

import astor.Astor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class is the entry point for the Astor application.
 * It extends {@code Application} to set up and display the main user interface.
 *
 * <p>The {@code start} method loads the main window from an FXML file, sets up the scene,
 * and injects an {@code Astor} instance into the {@code MainWindow} controller.</p>
 */
public class Main extends Application {

    private final Astor astor = new Astor("./src/main/data/astor.Astor.txt");

    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage must not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow> getController().setAstor(astor); // inject the Duke instance
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
