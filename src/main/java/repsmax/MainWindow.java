package repsmax;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The main window controller for the Repsmax application.
 * <p>
 * This class manages the user interface components of the main window,
 * including handling user input and displaying dialog messages.
 * </p>
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Repsmax repsmax;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window by binding the scroll pane's
     * vertical value to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the {@code Repsmax} instance for this controller.
     *
     * @param r The {@code Repsmax} instance to be set.
     */
    public void setRepsmax(Repsmax r) {
        this.repsmax = r;
    }

    /**
     * Handles user input by retrieving the text from the input field,
     * getting a response from the {@code Repsmax} instance, and
     * updating the dialog container with the new dialogs.
     */
    @FXML
    private void handleUserInput() {
        if (repsmax == null) {
            return; // Handle case where repsmax is not set
        }

        String input = userInput.getText();
        String response;
        try {
            response = repsmax.getResponse(input);
        } catch (Exception e) {
            response = "Sorry, something went wrong!";
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}

