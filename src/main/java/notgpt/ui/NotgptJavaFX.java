package notgpt.ui;


import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import notgpt.parsers.CommandParser;

/**
 * A JavaFX application that simulates a simple chatbot interface.
 *
 * The application provides a graphical interface where users can interact with a bot by sending messages.
 * It includes a chat window for displaying the conversation, an input field for user input, and a send button
 * to submit messages. User and bot messages are displayed with avatars, and the interface auto-scrolls to keep
 * the latest messages visible.
 *
 * Main features of the application include:
 * <ul>
 *   <li>A scrollable chat area that displays both user and bot messages</li>
 *   <li>An input field and send button for submitting messages</li>
 *   <li>Avatar images to represent user and bot in the conversation</li>
 *   <li>Automatic scrolling to keep the latest messages in view</li>
 * </ul>
 *
 * This class extends {@link javafx.application.Application} and overrides the {@code start} method
 * to set up and initialize the user interface.
 *
 * The application also processes basic commands like "bye" to exit.
 */
public class NotgptJavaFX extends Application {

    private static final int FONT_SIZE_INPUT = 25;
    private static final int FONT_SIZE = 16;
    private CommandParser parser = new CommandParser();
    private VBox chatBox;
    private TextField inputField;
    private Button sendButton;
    private ScrollPane scrollPane;
    private Image userAvatar;
    private Image botAvatar;


    /**
     * The main entry point for the JavaFX application.
     *
     * This method is called after the JavaFX runtime is initialized and the application is ready
     * to start. The method initializes and displays the scene, manages user interaction events,
     * and handles message display and scrolling functionality.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {


        // Set Application icon
        primaryStage.setTitle("Notgpt");
        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        primaryStage.getIcons().add(icon);

        // Load avatar images
        userAvatar = new Image(getClass().getResourceAsStream("/user_avatar.png"));
        botAvatar = new Image(getClass().getResourceAsStream("/bot_avatar.jpg"));
        assert userAvatar != null : "there is no user image";
        assert botAvatar != null : "there is no bot image";

        Circle clip = new Circle(45);

        // Create UI components
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setStyle("-fx-background-color: #ECE5DD;");

        scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
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
        scene.getStylesheets().add("css/ScrollbarStyle.css");

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
            if (command.equals("bye")) {
                this.exit();
            }
        }
    }

    private void displayMessage(String message, boolean isUser) {
        Text messageText = new Text(message);
        messageText.setFont(Font.font("System", FontWeight.NORMAL, FONT_SIZE));

        // Create TextFlow
        TextFlow textFlow = new TextFlow(messageText);
        textFlow.setPadding(new Insets(8));
        textFlow.setMaxWidth(220);
        double maxHeight = 40;
        textFlow.setMaxHeight(maxHeight);

        // Calculate preferred height
        double prefHeight = messageText.getLayoutBounds().getHeight() + 16; // Padding
        textFlow.setPrefHeight(Math.min(prefHeight, maxHeight));

        // Create avatar
        ImageView avatarView = new ImageView(isUser ? userAvatar : botAvatar);
        avatarView.setFitHeight(90);
        avatarView.setFitWidth(90);
        VBox avatarWrapper = new VBox();
        avatarWrapper.setMaxHeight(90);
        avatarWrapper.setMaxWidth(90);
        avatarWrapper.getChildren().add(avatarView);
        avatarWrapper.setPadding(new Insets(0, 0, 4, 0)); // Add bottom padding here

        Circle clip = new Circle(45, 45, 45);
        avatarView.setClip(clip);

        // Create Circle for avatar border
        Circle borderCircle = new Circle(45, 45, 45);
        borderCircle.setStroke(Color.WHITE);
        borderCircle.setStrokeWidth(8);
        borderCircle.setFill(Color.TRANSPARENT);

        // Create StackPane for avatar and border
        StackPane avatarContainer = new StackPane();
        avatarContainer.getChildren().addAll(borderCircle, avatarWrapper);
        avatarContainer.setAlignment(Pos.CENTER); // Align avatar to bottom

        StackPane avatar = new StackPane();
        avatar.getChildren().add(avatarContainer);
        avatarContainer.setAlignment(Pos.BOTTOM_CENTER);

        // Create HBox for message and avatar
        HBox messageBox = new HBox(10);
        messageBox.setAlignment(isUser ? Pos.BOTTOM_RIGHT : Pos.BOTTOM_LEFT);
        messageBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Add message text and avatar to HBox
        if (isUser) {
            textFlow.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 12 12 0 12;");
            messageBox.getChildren().addAll(textFlow, avatar);
        } else {
            textFlow.setStyle("-fx-background-color: white; -fx-background-radius: 12 12 12 0;");
            messageBox.getChildren().addAll(avatar, textFlow);
        }
        chatBox.getChildren().add(messageBox);

        Platform.runLater(() -> {
            scrollPane.layout(); // Force scrollPane to refresh layout
            scrollPane.setVvalue(1.0); // Scroll to the bottom
        });
    }

    private void exit() {
        // Pause for a few seconds to allow the user to see the message
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    private String processInput(String command, String text) {
        return parser.parse(command, text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
