package casper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Casper application.
 * This class manages the user interface, including displaying dialog boxes,
 * handling user input, and interacting with the {@link Casper} instance.
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
    private Casper casper;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/troll.png"));
    private Image casperImage = new Image(this.getClass().getResourceAsStream("/images/casper.png"));

    /**
     * Initializes the {@code MainWindow} by binding the scroll pane's vertical value property to
     * the dialog container's height property to ensure that the scroll pane scrolls automatically.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@link Casper} instance into this controller.
     *
     * @param c The {@link Casper} instance to be used for processing user input and generating responses.
     */
    public void setCasper(Casper c) {
        casper = c;
    }

    /**
     * Adds the start-up messages to the dialog container.
     * This method creates dialog boxes for the messages loaded from the {@link Casper} instance
     * and displays them in the dialog container.
     */
    public void addStartUpMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getCasperDialog(casper.loadSavedTasks(), casperImage),
                DialogBox.getCasperDialog(casper.welcomeUser(), casperImage));
    }

    /**
     * Handles the user input by creating dialog boxes for both the user's input and Casper's response,
     * and appends them to the dialog container. Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getCasperDialog(casper.getResponse(userText), casperImage));
        userInput.clear();
    }
}

