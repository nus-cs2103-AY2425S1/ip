package bean.gui;

import java.io.IOException;

import bean.Bean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * bean.gui.Main application class for Bean's GUI.
 */
public class Main extends Application {

    private Bean bean = new Bean("data/bean.txt");

    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBean(bean);  // inject the Bean instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
