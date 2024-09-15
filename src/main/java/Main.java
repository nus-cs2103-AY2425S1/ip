import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import sirpotato.SirPotato;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private SirPotato sp = new SirPotato("data/list.txt");
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("SirPotato chatbot");
            fxmlLoader.<MainWindow>getController().setBot(sp);  // inject the Bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
