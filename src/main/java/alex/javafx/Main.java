package alex.javafx;

import java.io.IOException;

import alex.Alex;
import alex.AlexException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Alex alex = new Alex("src/main/resources/data/Alex.txt");

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        try {
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setAlex(alex); // inject the Alex instance
            stage.show();
            this.alex.loadTasksFromFile();
        } catch (IOException | AlexException e) {
            fxmlLoader.<MainWindow>getController().showErrorMsgOnStart(e);
            MainWindow.exitApplication(2);
        }
    }
}
