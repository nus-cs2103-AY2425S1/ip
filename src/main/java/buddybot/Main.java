package buddybot;
//test
import java.io.IOException;
//test
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private BuddyBot buddy = new BuddyBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = myFxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            myFxmlLoader.<MainWindow>getController().setBuddy(buddy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}