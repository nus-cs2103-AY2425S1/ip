package bao.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Bao bao;

    /**
     * Starts the GUI
     *
     * @param stage Stage set up in FXML files
     */
    @Override
    public void start(Stage stage) {
        bao = new Bao("./data/bao.json.txt");
        assert bao != null : "Bao instance must not be null";

        try {
            stage.setMinHeight(660);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setBao(bao);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
