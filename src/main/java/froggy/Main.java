package froggy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Froggy using FXML.
 */
public class Main extends Application {

    //private Froggy froggy = new Froggy("./data/taskList.txt");
    private Froggy froggy;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Froggy");
            froggy = new Froggy("./data/taskList.txt");
            fxmlLoader.<MainWindow>getController().setFroggy(froggy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FroggyException e) {
            showErrorDialog("Error", "An error occured", e.getMessage() + "\nDelete or edit the file to continue.");
        }
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
