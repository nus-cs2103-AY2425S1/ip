package secondmind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private final static String EXIT_COMMAND = "$$$EXIT_PROGRAM$$$";

    private SecondMind secondMind;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image secondMindImage = new Image(this.getClass().getResourceAsStream("/images/brain.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Welcome to SecondMind!\nWhat can i help you with today?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, secondMindImage)
        );
    }

    /** Injects the Duke instance */
    public void setSecondMind(SecondMind secondMind) {
        this.secondMind = secondMind;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.secondMind.getResponse(input);
        if (response.equals(this.EXIT_COMMAND)) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, secondMindImage)
            );
            userInput.clear();
        }
    }
}
