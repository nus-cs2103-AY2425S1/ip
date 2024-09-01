package infinity.ui.components;

import java.util.Objects;

import infinity.Infinity;
import infinity.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main file to run for GUI version of Infinity Bot.
 */
public class GraphicsMainWindow extends AnchorPane {
    private final Image genericImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/GenericUser.png")));
    private final Image botImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/InfinityBotUser.png")));
    private boolean isBotAlive = true;

    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Button sendButton;

    private Infinity infinityBot;

    /**
     * Initialises the Graphics Main Window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        infinityBot = new Infinity(dialogContainer, botImage);
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (isBotAlive) {
            dialogContainer.getChildren().addAll(
                    DialogBox.createUserDialog("\nYou\n-----\n" + userInput.getText(), genericImage));
            String botOutput = Infinity.newUserInput(userInput.getText());
            dialogContainer.getChildren().addAll(DialogBox.createBotDialog(botOutput, botImage));
            if (botOutput.contains(Ui.BOT_END_STATEMENT)) {
                isBotAlive = false;
                dialogContainer.getChildren().addAll(
                        DialogBox.createBotDialog("Restart the app to start the bot again.", botImage));
            }
            userInput.clear();
        }
    }
}
