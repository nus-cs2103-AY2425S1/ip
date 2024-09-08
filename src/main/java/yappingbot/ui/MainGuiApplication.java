package yappingbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainGuiApplication class to launchGui GUI Application.
 */
public class MainGuiApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(
                    MainGuiApplication.class.getResource("/view/MainWindow.fxml"));
            VBox vb = fxmlloader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
