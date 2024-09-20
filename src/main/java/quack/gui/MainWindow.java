package quack.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import quack.Quack;
import quack.command.Command;

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
    /** Quack chatbot object */
    private Quack quack;
    /** Command to be executed */
    private Command command = null;
    /** Image of the user */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/person.png"));
    /** Image of the Quack chatbot */
    private Image quackImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Quack instance and greets the user.
     */
    public void setQuack() {

        quack = new Quack(dialogContainer, quackImage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Quacks's reply
     * and then appends them to the dialog container.
     * <p>
     * It will then clear the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        if (this.command == null) {
            this.command = quack.processResponse(input);
            if (this.command != null) {
                command.prompt();
            }
        } else {
            this.command.execute(input);
        }

        if (this.command != null && this.command.getCompletionStatus()) {
            this.command = null;
        }

        userInput.clear();
    }
}
