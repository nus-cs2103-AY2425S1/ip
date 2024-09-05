package duke.ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextBotApp extends Application {

    private TextArea chatArea;
    private TextField inputField;
    private Button sendButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Not-GPT Text Bot");

        // Create UI components
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        inputField = new TextField();
        sendButton = new Button("Send");

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(chatArea, inputField, sendButton);

        // Set up the scene
        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);

        // Event handling
        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnAction(e -> sendMessage());

        // Initial message
        displayMessage("Not-GPT: Hi, I'm Not-GPT. Do you really need me to do something for you?");

        primaryStage.show();
    }

    private void sendMessage() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            displayMessage("You: " + userInput);
            inputField.clear();

            // Process the user input and generate a response
            String response = processInput(userInput);
            displayMessage("Not-GPT: " + response);
        }
    }

    private void displayMessage(String message) {
        chatArea.appendText(message + "\n");
    }

    private String processInput(String input) {
        // Here you can integrate your existing Parser logic
        // For now, we'll just echo the input
        return "You said: " + input;
    }

    public static void main(String[] args) {
        launch(args);
    }
}