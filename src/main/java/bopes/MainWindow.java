package bopes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Controller for the main window of the Bopes application.
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

    private Bopes bopes;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bopesImage = new Image(this.getClass().getResourceAsStream("/images/bopes.png"));

    /**
     * Initializes the controller. Binds the scroll pane to automatically scroll to the
     * bottom when new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Bopes instance for this controller.
     *
     * @param bopes The Bopes instance to be used.
     */
    public void setBopes(Bopes bopes) {
        this.bopes = bopes;
    }

    /**
     * Handles user input, processes the command, and displays the corresponding response
     * in the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        CommandResult result = bopes.parseInput(input);

        dialogContainer.getChildren().add(
            DialogBox.getUserDialog(input, userImage, false)
        );

        dialogContainer.getChildren().add(
            DialogBox.getBopesDialog(result.getFeedbackToUser(), bopesImage, result.isError())
        );

        userInput.clear();
    }
}
