package blob;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image blobImg = new Image(this.getClass().getResourceAsStream("/images/DaBlob.png"));
    private Blob blob = new Blob("./database.csv", userImg, blobImg);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Blob");
            fxmlLoader.<MainWindow>getController().setBlob(blob);  // inject Blob instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
