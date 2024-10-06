package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.TaskFairy;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TaskFairy taskFairy = new TaskFairy();
    private Image appIcon = new Image(this.getClass().getResourceAsStream("/gui/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("\u2728\uD83E\uDDDA\u200D\u2640\uFE0FTaskFairy\uD83E\uDDDA\u200D\u2640\uFE0F\u2728");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gui/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskFairy(taskFairy);  // inject the Duke instance
            stage.getIcons().add(appIcon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
