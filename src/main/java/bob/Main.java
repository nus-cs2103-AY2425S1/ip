package bob;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bob using FXML. Code adapted from https://se-education.org/guides/tutorials/javaFx.html
 */
public class Main extends Application {

    private Bob bob;

    @Override
    public void start(Stage stage) {
        try {
            bob = new Bob("./data/bob.txt");
        } catch (FileNotFoundException e) {
            bob = new ErrorBob("Seems like I'm missing my memory");
        } catch (IOException e) {
            bob = new ErrorBob("I'm having trouble initialising my memory :(");
        }
        assert bob != null : "bob variable cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBob(bob); // inject the Bob instance
            fxmlLoader.<MainWindow>getController().welcomeUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}