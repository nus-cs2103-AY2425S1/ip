package orion;

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

    private Orion orion;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image orionImage = new Image(this.getClass().getResourceAsStream("/images/orion.png"));

    /**
     * Initializes the GUI components.
     * 
     * <p>
     * This is a FXML callback method that is called automatically when the
     * {@link FXMLLoader} creates the {@link Node} objects for the GUI.
     * </p>
     * 
     * <p>
     * This method binds the vertical scroll bar of the {@link ScrollPane} to
     * the height property of the {@link VBox} that contains the dialog
     * messages. This causes the scroll bar to always be at the bottom of the
     * scroll pane.
     * </p>
     * 
     * @FXML
     *       public void initialize() {
     *       scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
     *       }
     * 
     *       /** Injects the Orion instance
     */
    /**
     * Injects the Orion instance.
     * 
     * <p>
     * This is a setter method that is used by the FXML framework to inject the
     * {@link Orion} instance into this controller.
     * </p>
     * 
     * @param d the Orion instance
     */
    public void setOrion(Orion d) {
        orion = d;
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        String welcomeMessage = orion.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getOrionDialog(welcomeMessage, orionImage));
    }

    @FXML
    /**
     * Handles user input and displays the response from Orion.
     *
     * <p>
     * This method is called when the user presses the Enter key in the text
     * input box. It retrieves the user input, sends it to the Orion instance
     * to get a response, and then displays the response as a dialog message.
     * It also clears the user input box after processing.
     * </p>
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = orion.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOrionDialog(response, orionImage));
        userInput.clear();
    }
}
