package cheese;
import java.io.IOException;

import cheese.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for chatbot using FXML.
 */
public class Main extends Application {
    private WheelyBigCheese bot = new WheelyBigCheese();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCheese(bot); // inject the bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
