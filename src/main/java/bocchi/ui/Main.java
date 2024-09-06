package bocchi.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private BocchiWrapper bocchiWrapper = new BocchiWrapper();

    @Override
    public void start(Stage stage) {
        try {
            System.out.println(Main.class.getResource("/resources/view/MainWindow.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(300);
            stage.setMinWidth(500);
            fxmlLoader.<MainWindow>getController().setBocchiWrapper(bocchiWrapper);  // inject the Duke instance
            bocchiWrapper.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
