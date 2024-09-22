package botimusprime;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for BotimusPrime using FXML.
 */
public class Main extends Application {

    private BotimusPrime botimusPrime = new BotimusPrime("todolist.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Botimus Prime");

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setBotimusPrime(botimusPrime);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}