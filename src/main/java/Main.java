import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import winner.Winner;

/**
 * A GUI for Winner using FXML.
 */
public class Main extends Application {

    private Winner winner = new Winner();

    @Override
    public void start(Stage stage) {
        try {
            stage.setMaxHeight(524);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap, 800, 524);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWinner(winner);  // inject the Duke instance
            stage.show();
            System.out.println("Width: " + stage.getWidth());
            System.out.println("Height: " + stage.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
