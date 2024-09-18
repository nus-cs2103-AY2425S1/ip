package bruno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Bruno bruno = new Bruno();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            // Get the controller and set bruno
            MainWindow controller = fxmlLoader.getController();
            controller.setBruno(bruno);

            // Display Greeting message
            controller.showGreeting();

            stage.setTitle("Bruno Bucciarati Bot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
