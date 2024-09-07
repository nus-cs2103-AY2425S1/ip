package elliot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import ui.DialogBox;
import ui.MainWindow;

public class MainGui extends Application {
    private Elliot elliot = new Elliot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainGui.class
                    .getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setElliot(elliot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
