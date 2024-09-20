package chatbot;

import java.util.Scanner;

import chatbot.impl.ui.AtlasView;
import chatbot.impl.Command;
import chatbot.impl.Atlas;
import chatbot.impl.TaskStorageImpl;
import chatbot.interfaces.ChatBot;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorage;

// Imports for GUI
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import chatbot.impl.ui.DialogBox;


public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       MessageView<Command> messageView = new AtlasView();
       TaskStorage<Command> taskStorage = new TaskStorageImpl("data/atlas.txt");
       ChatBot bot = new Atlas(scanner, messageView, taskStorage);
       bot.start();
    }

   @Override
   public void start(Stage stage) {
        //Setting up required components

       scrollPane = new ScrollPane();
       dialogContainer = new VBox();
       scrollPane.setContent(dialogContainer);

       userInput = new TextField();
       sendButton = new Button("Send");

//        DialogBox dialogBox = new DialogBox("Hello!", userImage);
//        dialogContainer.getChildren().addAll(dialogBox);

       AnchorPane mainLayout = new AnchorPane();
       mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

       scene = new Scene(mainLayout);

       stage.setScene(scene);
       stage.show();

        //More code to be added here later
   }
}
