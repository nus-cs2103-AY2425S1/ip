import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import spiderman.Spiderman;
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

    private Spiderman spiderman;

    private Image spidermanImage = new Image(this.getClass().getResourceAsStream("/images/spiderman.png"), 200, 150, true, false);
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Peter.png"), 200, 150, true, false);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Spiderman instance */
    public void setSpiderman(Spiderman s) {
        spiderman = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Spiderman's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = spiderman.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSpidermanDialog(response, spidermanImage)
        );
        userInput.clear();
        if (spiderman.checkIsExit()) {
            Platform.exit();
        }
    }

    public void displayWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getSpidermanDialog(spiderman.getWelcome(), spidermanImage)
        );
    }
}
