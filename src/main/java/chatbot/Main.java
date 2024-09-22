package chatbot;

import java.util.Scanner;

import chatbot.impl.AtlasGUI;
import chatbot.impl.ui.AtlasView;
import chatbot.impl.Command;
import chatbot.impl.Atlas;
import chatbot.impl.TaskStorageImpl;
import chatbot.interfaces.ChatBot;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorage;

// Imports for GUI
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import chatbot.impl.gui.MainWindow;


public class Main extends Application {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       MessageView<Command> messageView = new AtlasView();
       TaskStorage<Command> taskStorage = new TaskStorageImpl("data/atlas.txt");
       ChatBot bot = new Atlas(scanner, messageView, taskStorage);
       bot.start();
    }

    private ChatBot bot;

    public void initBot() {
        Scanner scanner = new Scanner(System.in);
        MessageView<Command> messageView = new AtlasView();
        TaskStorage<Command> taskStorage = new TaskStorageImpl("data/atlas.txt");
        bot = new AtlasGUI(scanner, messageView, taskStorage);
    }

    @Override
    public void start(Stage stage) {
        try {
            initBot();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBot(bot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
