package swbot;

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

    private R2D2 r2d2;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Luke.png"));
    private Image r2d2Image = new Image(this.getClass().getResourceAsStream("/images/r2d2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the R2D2 instance */
    public void setR2D2(R2D2 r) {
        r2d2 = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing R2D2's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = r2d2.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, r2d2Image)
        );
        userInput.clear();
    }
}
