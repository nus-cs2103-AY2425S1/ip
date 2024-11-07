package chatbuddy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class represents the entry point for the ChatBuddy GUI application using JavaFX.
 */
public class Main extends Application {

    private ChatBuddy chatbuddy = new ChatBuddy("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ChatBuddy");
            fxmlLoader.<MainWindow>getController().setChatBuddy(chatbuddy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
