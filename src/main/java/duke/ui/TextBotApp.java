package duke.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class TextBotApp extends Application {

    private VBox chatBox;
    private TextField inputField;
    private Button sendButton;
    private Image userAvatar;
    private Image botAvatar;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Notgpt");

        // Load avatar images
        userAvatar = new Image(getClass().getResourceAsStream("/user_avatar.png"));
        botAvatar = new Image(getClass().getResourceAsStream("/bot_avatar.jpg"));

        // Create UI components
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setStyle("-fx-background-color: #ECE5DD;");

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        inputField = new TextField();
        inputField.setPromptText("Type a message");
        inputField.setPrefHeight(40);

        sendButton = new Button("Send");
        sendButton.setPrefHeight(40);
        sendButton.setStyle("-fx-background-color: #128C7E; -fx-text-fill: white;");

        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));
        inputBox.setStyle("-fx-background-color: #F0F0F0;");
        HBox.setHgrow(inputField, Priority.ALWAYS);
        inputBox.getChildren().addAll(inputField, sendButton);

        // Layout
        VBox root = new VBox();
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        root.getChildren().addAll(scrollPane, inputBox);

        // Set up the scene
        Scene scene = new Scene(root, 350, 600);
        primaryStage.setScene(scene);

        // Event handling
        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnAction(e -> sendMessage());

        // Initial message
        displayMessage("Hi, I'm Not-GPT. Do you really need me to do something for you?", false);

        primaryStage.show();
    }

    private void sendMessage() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            displayMessage(userInput, true);
            inputField.clear();

            // Process the user input and generate a response
            String response = processInput(userInput);
            displayMessage(response, false);
        }
    }

    private void displayMessage(String message, boolean isUser) {
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setPadding(new Insets(8));
        messageLabel.setMaxWidth(220);

        ImageView avatarView = new ImageView(isUser ? userAvatar : botAvatar);
        avatarView.setFitHeight(40);
        avatarView.setFitWidth(40);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        if (isUser) {
            messageLabel.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 10;");
            messageBox.getChildren().addAll(messageLabel, avatarView);
        } else {
            messageLabel.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
            messageBox.getChildren().addAll(avatarView, messageLabel);
        }

        chatBox.getChildren().add(messageBox);
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
