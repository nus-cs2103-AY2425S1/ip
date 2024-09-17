package slothingwaffler;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private final SlothingWaffler slothingWaffler = new SlothingWaffler("data.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Slothing Waffler");
            stage.getIcons().add(new Image("/images/sloth.png"));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSlothingWaffler(slothingWaffler);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
