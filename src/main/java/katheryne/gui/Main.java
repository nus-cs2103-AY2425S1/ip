package katheryne.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import katheryne.Katheryne;


public class Main extends Application {
    private Katheryne katheryne = new Katheryne("./data/Katheryne.txt");
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Piamon.png"));
    private Image katheryneImage = new Image(this.getClass().getResourceAsStream("/images/Katheryne.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Katheryne");
            fxmlLoader.<MainWindow>getController().setKatheryne(katheryne);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
