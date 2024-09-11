package nugget.gui;

import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import nugget.Nugget;

/**
 * The {@code ChatUI} class represents the user interface for a chat application.
 * It handles the display of messages and user input, including the text field for
 * user commands, the submit button, and the scrollable message area.
 * The {@code ChatUI} class uses JavaFX components to create a GUI for interacting
 * with the {@code Nugget} backend.
 */
public class ChatUI {

    private Nugget nugget;
    private VBox messageArea;
    private TextField inputField;
    private ScrollPane scrollPane;
    private Button submitButton;

    private Image userImage;
    private Image botImage;

    /**
     * Constructs a ChatUI object with the specified Nugget instance.
     * @param nugget
     */
    public ChatUI(Nugget nugget) {
        this.nugget = nugget;
        initializeComponents();
    }

    /**
     * Initialises the UI components including the message area, input field, and submit button.
     * Sets up the layout and ensures that images are loaded correctly.
     * <p>
     * This method also includes assertions to verify that images are not {@code null} and that the layout
     * contains the expected components.
     * </p>
     */

    private void initializeComponents() {
        userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/nugget.jpeg")));
        botImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/bot.jpeg")));

        assert userImage != null : "User image should not be null";
        assert botImage != null : "Bot image should not be null";

        // Message area (Scrollable)
        messageArea = new VBox(10);
        scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Automatically scroll to the bottom when new messages are added
        messageArea.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));

        inputField = new TextField();
        inputField.setPromptText("Enter command...");
        inputField.setOnAction(e -> handleInput());
        HBox.setHgrow(inputField, Priority.ALWAYS);

        // Submit button
        submitButton = new Button("Submit");
        submitButton.setOnAction(e -> handleInput());

        // Input field and button side by side
        HBox inputLayout = new HBox(10, inputField, submitButton);
        inputLayout.setAlignment(Pos.BOTTOM_CENTER);

        VBox layout = new VBox(10, scrollPane, inputLayout);
        assert layout.getChildren().size() == 2 : "Layout should only contain scroll pane and input field";
        layout.setPadding(new Insets(10));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    public VBox getLayout() {
        return new VBox(scrollPane, new HBox(inputField, submitButton));
    }

    private void handleInput() {
        String input = inputField.getText();
        if (!input.trim().isEmpty()) {
            addMessage(input, true);
            nugget.handleInput(input);
            inputField.clear();
        }
    }

    /**
     * Adds a message to the message area. The message can either be from user or bot.
     * @param message The content to be added.
     * @param isUser A boolean indicating whether the message belongs to the user. It is used
     *               in formatting the message in message area.
     */
    public void addMessage(String message, boolean isUser) {
        HBox messageBox = MessageHandler.createMessageBox(message, isUser, userImage, botImage);
        messageArea.getChildren().add(messageBox);
    }
}
