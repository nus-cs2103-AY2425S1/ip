package pochat.display;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pochat.bot.PoChat;
/**
 * A GUI for PoChat using FXML.
 */
public class Main extends Application {

    private PoChat poChat = new PoChat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPoChat(poChat);
            stage.show();
            sayHello();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHello() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hello! I am PoChat, the chatbot in your pocket. "
            + "Type something below to start!");
        alert.showAndWait();
    }
}
