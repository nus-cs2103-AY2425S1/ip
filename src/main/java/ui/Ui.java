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
public class Ui extends AnchorPane {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

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

    /**
     * Generates a response by the bot that is visible in the GUI.
     * @param response - response (as a string) that the bot should return.
     */
    @FXML
    public void printGenericFeedback(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getGrokDialog(response, botImage)
        );
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Grok instance */
    public void setGrok(Grok g) {
        grok = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Grok's reply and then appends them to
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
