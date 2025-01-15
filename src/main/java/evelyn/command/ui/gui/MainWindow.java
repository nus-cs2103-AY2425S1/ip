package evelyn.command.ui.gui;

import java.util.Objects;

import evelyn.Evelyn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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

    private Evelyn evelyn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image evelynImage = new Image(this.getClass().getResourceAsStream("/images/Evelyn.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Evelyn instance */
    public void setEvelyn(Evelyn e) {
        evelyn = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Evelyn's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = evelyn.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, evelynImage)
        );
        if (Objects.equals(response, "Bye Bye!!")) {
            Platform.exit();
            System.exit(0);
        }
        userInput.clear();
    }

    /**
     * Shows the user the message said by Evelyn
     * @param input Message to be shown to the user
     */
    public void addMessage(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(input, evelynImage)
        );
    }
}

