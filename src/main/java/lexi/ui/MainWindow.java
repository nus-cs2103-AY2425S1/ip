package lexi.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lexi.Lexi;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lexi lexi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lexiImage = new Image(this.getClass().getResourceAsStream("/images/DaLexi.png"));

    /**
     * Initialises the scrollpane and dialog box with lexi greeting
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getLexiGreeting("Hi! I'm Lexi!\n"
                + "What can I help you with today?", lexiImage));
    }

    /** Injects the Lexi instance */
    public void setLexi(Lexi d) {
        lexi = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lexi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lexi.getResponse(input);
        String commandType = lexi.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLexiDialog(response, lexiImage, commandType)
        );
        userInput.clear();
    }
}
