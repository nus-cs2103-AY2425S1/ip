package evan.gui;

import java.util.Objects;

import evan.service.Evan;
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

    private Evan evan;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image evanImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Evan.png")));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Evan instance.
     */
    public void setEvan(Evan e) {
        evan = e;
    }

    /**
     * Creates two dialog boxes - one echoing user input and the other containing Evan's reply - and appends them to the
     * dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = evan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEvanDialog(response, evanImage)
        );
        userInput.clear();
    }
}
