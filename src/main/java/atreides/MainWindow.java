package atreides;

import atreides.ui.Atreides;
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

    private Atreides atreides;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.jpg"));
    private Image atreidesImage = new Image(this.getClass().getResourceAsStream("/images/AtreidesImage.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setAtreides(Atreides a) {
        atreides = a;
        DialogBox dialogBox = DialogBox.getAtreidesDialog(atreides.showWelcome(), atreidesImage);
        dialogContainer.getChildren().addAll(dialogBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = atreides.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAtreidesDialog(response, atreidesImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            javafx.application.Platform.exit();
        }
    }
}
