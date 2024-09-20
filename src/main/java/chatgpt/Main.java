package chatgpt;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

    private static final String DEFAULT_SAVE_FILE_PATH = "\\data\\saveData.txt";

    private ChatGpt chatGpt;

    public Main(String filePath) {
        this.chatGpt = new ChatGpt(filePath);
    }

    public Main() {
        this(DEFAULT_SAVE_FILE_PATH);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChat(chatGpt);  // inject the chatbot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
