import java.io.File;
import java.util.Scanner;
import controllers.InputParser;
import controllers.commands.Command;
import controllers.errors.InvalidCommandError;
import controllers.errors.InvalidInputError;
import controllers.OutputHandler;
import lib.DbDriverInterface;
import lib.FileDbDriver;
import models.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Wojak extends Application {

    private InputParser parser = new InputParser();
    private DbDriverInterface dbDriver = new FileDbDriver();
    private TaskList taskList = new TaskList(dbDriver);

    @Override
    public void start(Stage stage) {
        // TextArea for displaying chat conversation
        // Initialize the TextArea
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);  // User can't directly edit the chat history
        chatArea.setWrapText(true);   // Enable text wrapping

        // TextField for user input
        TextField userInput = new TextField();
        userInput.setPromptText("Type your command here...");

        OutputHandler outputHandler = new OutputHandler(chatArea);

        // Button to send message
        Button sendButton = new Button("Send");

        // Handle send button click
        sendButton.setOnAction(e -> {
            String input = userInput.getText();
            if (!input.isEmpty()) {
                // Append user message to chat area
                chatArea.appendText("User: " + input + "\n");

                // Process user input using the existing Wojak logic
                try {
                    Command command = parser.parse(input);
                    command.execute(taskList, outputHandler);
                    chatArea.appendText("Bot: Command executed successfully.\n");
                } catch (Exception ex) {
                    chatArea.appendText("Bot: " + ex.getMessage() + "\n");
                }

                // Clear the input field
                userInput.clear();
            }
        });
        // Layout the components in a VBox
        VBox layout = new VBox(10, chatArea, userInput, sendButton);

        // Create the scene and set it on the stage
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Wojak Chatbot");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Start the JavaFX application
    }
}