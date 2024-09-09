package quack.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /** Image of the Quack chatbot */
    private Image quackImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setQuack();
            stage.setTitle("Quack - Enslaved duck to do your bidding");
            stage.getIcons().add(quackImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
