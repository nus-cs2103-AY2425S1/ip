package matcha;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Matcha matcha;
    private Image matchaImage = new Image(this.getClass().getResourceAsStream("/images/matcha.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    /**
     * Initializes the user interface of the app.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Matcha object to be used by the app.
     *
     * @param m The Matcha object to be used.
     */
    public void setMatcha(Matcha m) {
        matcha = m;
        dialogContainer.getChildren().addAll(
                DialogBox.getMatchaDialog(matcha.getWelcomeMessage(), matchaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Matcha's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = matcha.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMatchaDialog(response, matchaImage)
        );
        userInput.clear();
        //if Matcha is set to exit, close the window
        if (matcha.hasExit()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                closeWindow();
            });
            pause.play();
        }
    }
    /**
     * Closes the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.close();
    }
}
