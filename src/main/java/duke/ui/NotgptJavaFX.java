package duke.ui;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class NotgptJavaFX extends Application {

    private Parser parser = new Parser();
    private VBox chatBox;
    private TextField inputField;
    private Button sendButton;
    private Image userAvatar;
    private Image botAvatar;
    private static final int FONT_SIZE_INPUT = 25;
    private static final int FONT_SIZE = 16;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Notgpt");
        
        // Load avatar images
        userAvatar = new Image(getClass().getResourceAsStream("/user_avatar.png"));
        botAvatar = new Image(getClass().getResourceAsStream("/bot_avatar2.jpg"));

        // Create UI components
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setStyle("-fx-background-color: #ECE5DD;");

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        inputField = new TextField();
        inputField.setPromptText("Type a message");
        inputField.setPrefHeight(80);
        inputField.setFont(Font.font("System", FontWeight.NORMAL, FONT_SIZE_INPUT));

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
        VBox vBoxRoot = new VBox();
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        vBoxRoot.getChildren().addAll(scrollPane, inputBox);

        // Set up the scene
        Scene scene = new Scene(vBoxRoot, 500, 800);
        primaryStage.setScene(scene);

        // Event handling
        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnAction(e -> sendMessage());

        String logo = "_   _       _                       _\n"
                + "| \\ | |  _  | |_        __  __    | |__\n"
                + "|  \\| |/ _ \\  __|    / _` | '_ \\ |  __|\n"
                + "| |\\  | (_) | |_     | (_|  | |_)  |  |_\n"
                + "|_| \\_|\\__/ \\__|   \\__, | __/  \\__|\n"
                + "                       |___/|_|\n";
        // Initial message
        displayMessage(logo + "\n" + "hi, I'm Not-gpt,\ndo you really need me to do sth for you?", false);
        primaryStage.show();
    }

    private void sendMessage() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            String command;
            String text = "";
            String[] parts = userInput.split("\\s+", 2);
            command = parts[0].toLowerCase();
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                text = parts[1];
            }
            displayMessage(userInput, true);
            inputField.clear();
            String response = processInput(command, text);
            displayMessage(response, false);
            if(command.equals("bye")) {
                this.exit();
            }
        }
    }

    private void displayMessage(String message, boolean isUser) {
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setPadding(new Insets(8));
        messageLabel.setMaxWidth(220);
        messageLabel.setFont(Font.font("System", FontWeight.NORMAL, FONT_SIZE));

        ImageView avatarView = new ImageView(isUser ? userAvatar : botAvatar);
        avatarView.setFitHeight(90);
        avatarView.setFitWidth(90);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(isUser ? Pos.BOTTOM_RIGHT : Pos.BOTTOM_LEFT);

        if (isUser) {
            messageLabel.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 10;");
            messageBox.getChildren().addAll(messageLabel, avatarView);
        } else {
            messageLabel.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
            messageBox.getChildren().addAll(avatarView, messageLabel);
        }
        chatBox.getChildren().add(messageBox);
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }

    private void exit() {
        // Pause for a few seconds to allow the user to see the message
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    private String processInput(String command, String text) {
        return parser.parse(this, command, text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
