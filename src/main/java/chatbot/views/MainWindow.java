package chatbot.views;

import java.util.Objects;

import chatbot.logic.Bobby;
import chatbot.logic.Ui;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main window of the GUI application
 */
public class MainWindow extends AnchorPane {
    /** ScrollPane representing the scrollable pane of the application */
    @FXML
    private ScrollPane scrollPane;
    /** VBox representing the container of the chat */
    @FXML
    private VBox dialogContainer;
    /** TextField representing the chat input field */
    @FXML
    private TextField userInput;
    /** Button representing the send button */
    @FXML
    private Button sendButton;
    /** Scene representing the overall scene */
    @FXML
    private Scene scene;
    /** Bobby object with chatbot functionality */
    private Bobby bobby;

    /** Image representing the display image of the user */
    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    /** Image representing the display image of the bot */
    private Image botImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    /**
     * Initialises the GUI, and sends a welcome message to the user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBotDialog(Ui.sayHi(), botImage));
    }

    /**
     * Sets the Bobby instance
     * @param bobby The chatbot instance to be set
     */
    public void setBobby(Bobby bobby) {
        this.bobby = bobby;
    }

    /**
     * Creates 2 dialog boxes, one echoing user input and the other containing bot's reply
     * Then, appends them to the dialog container. Clears input after processing user input
     */
    @FXML
    public void handleUserInput() {
        String userCommand = userInput.getText();
        String botText = bobby.getResponse(userCommand);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userCommand, userImage),
                DialogBox.getBotDialog(botText, botImage)
        );
        userInput.clear();
    }
}
