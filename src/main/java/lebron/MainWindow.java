package lebron;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the LeBron ChatBot application.
 * This class handles user interactions with the graphical interface.
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

    private LeBron lebron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/lebron.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window by setting up the scroll pane to automatically scroll
     * to the bottom as new dialog boxes are added to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the LeBron instance into this controller.
     *
     * @param bron The LeBron instance to be used for handling user input.
     */
    public void setDuke(LeBron bron) {
        lebron = bron;
    }

    /**
     * Handles user input by creating two dialog boxes: one echoing the user's input and
     * the other containing LeBron's reply. Both dialog boxes are then appended to the
     * dialog container. Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lebron.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
