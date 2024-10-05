package bocchi.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private BocchiWrapper bocchiWrapper = new BocchiWrapper();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(300);
            stage.setMinWidth(600);

            stage.setOnCloseRequest(e -> bocchiWrapper.onExit());

            InputStream is = this.getClass().getResourceAsStream("/images/bocchi.jpg");
            if (is != null) {
                Image icon = new Image(is);
                stage.getIcons().add(icon);
            }

            stage.setTitle("Bocchi Chatbot");

            fxmlLoader.<MainWindow>getController().setBocchiWrapper(bocchiWrapper);  // inject the Duke instance
            bocchiWrapper.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
