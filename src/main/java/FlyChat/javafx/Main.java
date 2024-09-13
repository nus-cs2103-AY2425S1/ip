package flychat.javafx;
import java.io.IOException;

import flychat.core.FlyChat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private FlyChat flyChat = new FlyChat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFlyChat(flyChat);  // inject the FlyChat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
