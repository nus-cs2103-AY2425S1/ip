package megamind.gui;

import java.io.IOException;

import megamind.main.Megamind;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private final Megamind megamind = new Megamind();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Megamind");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMegamind(megamind);
            stage.setOnCloseRequest(event -> handleCloseRequest());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCloseRequest() {
        Megamind.exit();
    }
}
