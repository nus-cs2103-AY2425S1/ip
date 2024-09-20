package shrimp.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shrimp.Shrimp;

public class Main extends Application {
    private final Shrimp shrimp = new Shrimp();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Shrimp");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/shrimp.jpeg")));
            fxmlLoader.<MainWindow>getController().setShrimp(shrimp); // inject the Shrimp instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        shrimp.saveTasks();
    }
}
