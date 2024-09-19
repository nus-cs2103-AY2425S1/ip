package ponderpika.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ponderpika.PonderPika;

/**
 * This Main class helps in creating GUI for our PonderPika
 */
public class Main extends Application {
    private PonderPika ponderPika = new PonderPika("./data/pika.txt");

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Ponder Pika - Personal Task Manager!");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/images/pikabot.png"))));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(900);
            fxmlLoader.<MainWindow>getController().setPonderPika(ponderPika);
            stage.setOnCloseRequest(event -> this.ponderPika.saveFile());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
