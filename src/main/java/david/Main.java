package david;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for David using FXML.
 */
public class Main extends Application {

    private David chatbot = new David();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);


            stage.setTitle("David");
            Image davidLogo = new Image("/images/DavidLogo.png");
            stage.getIcons().add(davidLogo);

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setDavid(chatbot); // inject the David instance
            controller.launchChatBot();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
