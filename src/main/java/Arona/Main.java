package arona;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Arona using FXML.
 */
public class Main extends Application {

    private Arona arona = new Arona();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("MomoTalk");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/App_Icon.png")));
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setArona(arona);  // inject the Arona instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
