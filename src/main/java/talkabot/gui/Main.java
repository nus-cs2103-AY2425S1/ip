package talkabot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import talkabot.TalkaBot;

/**
 * A GUI for Talk-a-Bot using FXML.
 */
public class Main extends Application {

    private TalkaBot talkabot = new TalkaBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Talk-a-Bot");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDuke(talkabot); // inject the Talk-a-Bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



