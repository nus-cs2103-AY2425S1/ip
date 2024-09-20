package sammy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sammy.GUI.MainWindow;

public class Main extends Application {

    private Sammy sammy = new Sammy("./sammy.txt");

    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage cannot be null";
        assert sammy != null : "Sammy instance cannot be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSammy(sammy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
