import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import strand.Strand;

/**
 * GUI class for Strand.
 */
public class Main extends Application {
    private final Strand strand = new Strand("./data/strand.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStrand(strand);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
