package alexer.ui;

import alexer.Alexer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle(Alexer.NAME);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
