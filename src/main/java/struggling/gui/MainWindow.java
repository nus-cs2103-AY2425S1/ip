package struggling.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import struggling.Struggling;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this
            .getClass()
            .getResourceAsStream("/images/user.gif"));
    private final Image dukeImage = new Image(this
            .getClass()
            .getResourceAsStream("/images/struggling.gif"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Struggling bot;

    /**
     * Initialize scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane
                .vvalueProperty()
                .bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     */
    public void setBot(Struggling s) {
        bot = s;
        dialogContainer
                .getChildren()
                .addAll(DialogBox.getBotDialog(bot.getGreeting(), dukeImage, "Welcome"));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
     * appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        String commandType = bot.getCommandType();
        dialogContainer
                .getChildren()
                .addAll(DialogBox.getUserDialog(input, userImage),
                        DialogBox.getBotDialog(response, dukeImage, commandType));
        userInput.clear();
    }
}
