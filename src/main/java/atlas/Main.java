package atlas;

import java.io.IOException;

import atlas.commands.CommandManager;
import atlas.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Atlas using FXML.
 */
public class Main extends Application {
    private final String filepath = "./data/atlas.txt";
    private final Atlas atlas = new Atlas(filepath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAtlas(atlas); // inject the Atlas instance
            stage.show();
            CommandManager.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
