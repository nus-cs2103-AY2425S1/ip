package ui;

import grok.DialogBox;
import grok.Grok;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Encapsulates the application and the means to communicate with users using messages.
 */
/**
 * Controller for the main GUI.
 */
public class Ui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Grok grok;

    /**
     * Pad a provided message with horizontal lines and indentation to separate bot input from user input
     * @param msg - Message the bot should return to the user
     * @return Standard bot message formatted message, easily distinguishable from user messages
     */
    private static String padMessage(String msg) {
        // first stage - add horizontal lines above and below the message
        String horizontalLine = "_".repeat(80);
        String msgWithHLines = String.join("\n", horizontalLine, msg, horizontalLine);

        // second stage - provide 1 indentation to all lines of the message, including the horizontal line
        String indentSpaces = " ".repeat(4);

        return indentSpaces.concat(msgWithHLines.replace("\n", "\n".concat(indentSpaces)));
    }

    @FXML
    public void printGenericFeedback(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    public void printByeMessage() {
        printGenericFeedback("Bye. Hope to see you again soon!");
    }

    public void printErrorMessage(String s) {
        printGenericFeedback("Error! " + s);
    }

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Grok instance */
    public void setGrok(Grok g) {
        grok = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        printGenericFeedback(grok.processResponse(input));

        userInput.clear();
    }
}
