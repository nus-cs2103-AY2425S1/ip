package lawrence.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lawrence.app.Lawrence;
import lawrence.ui.components.MainWindow;

/**
 * A GUI for Lawrence using FXML.
 */
public class Main extends Application {
    private final Lawrence lawrence = new Lawrence();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Lawrence Chatbot");

            fxmlLoader.<MainWindow>getController().setLawrence(lawrence);
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
