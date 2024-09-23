package chatbot;

import java.io.IOException;
import java.util.Scanner;

import chatbot.impl.KatChatBotImpl;
import chatbot.impl.MessageParserImpl;
import chatbot.impl.TaskStorageImpl;
import chatbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class contains the entry point of the application.
 * It initializes and starts the chatbot.
 */
public class Main extends Application {

    /**
     * Sets up the necessary components and starts the chatbot.
     *
     * @param stage The primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorageImpl();
        MessageParser messageParser = new MessageParserImpl(taskStorage);
        ChatBot chatBot = new KatChatBotImpl(scanner, messageParser);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatbot(chatBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
