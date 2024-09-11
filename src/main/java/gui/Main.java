package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import slave.Slave;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {

    private Slave slave = new Slave("./src/main/data/savefile.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSlave(slave); // inject the slave.Slave instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
