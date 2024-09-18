import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Hamyo hamyo = new Hamyo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Hamyo Chatbot");
            stage.setMinHeight(630);
            stage.setMinWidth(420);
            fxmlLoader.<MainWindow>getController().setHamyo(hamyo); // inject the Hamyo instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
