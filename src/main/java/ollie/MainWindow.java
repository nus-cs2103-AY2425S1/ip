package ollie;

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

    private Ollie ollie;

    private ExitHandler exitHandler;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pug.png"));
    private Image ollieImage = new Image(this.getClass().getResourceAsStream("/images/ollie.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Ollie instance
     */
    public void setOllie(Ollie o) {
        ollie = o;

        // Show the initial message after Ollie is set
        dialogContainer.getChildren().addAll(
                DialogBox.getOllieDialog(ollie.getInitialMessage(), ollieImage)
        );
    }

    /**
     * Set callback function
     */
    public void setExitHandler(ExitHandler exitHandler) {
        this.exitHandler = exitHandler;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ollie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = ollie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOllieDialog(response.getMessage(), ollieImage)
        );
        userInput.clear();

        // Signal to exit
        if (response.isExit() && exitHandler != null) {
            exitHandler.handleExit();
        }
    }
}