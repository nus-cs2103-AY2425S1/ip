import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class represents the main window for the application's GUI.
 * It handles user interactions and displays messages between the user and Alex.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/DaAlex.jpg"));

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value
     * to the dialog container's height property, ensuring the scroll pane follows
     * the dialog container's height as new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Alex instance to this window.
     * This method also triggers the display of Alex's welcome message
     * when the application starts.
     *
     * @param alex The instance of Alex used to handle user inputs and generate responses.
     */
    public void setAlex(Alex alex) {
        this.alex = alex;
        displayWelcomeMessage(); // Display the welcome message upon initialization
    }

    /**
     * Displays the welcome message from Alex in the dialog container.
     * The message is displayed when Alex is initialized and injected into the window.
     */
    private void displayWelcomeMessage() {
        String welcomeMessage = alex.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getAlexDialog(welcomeMessage, alexImage));
    }

    /**
     * Handles the user input by retrieving the text entered by the user,
     * generating Alex's response, and updating the dialog container with
     * both the user's input and Alex's response. It then clears the user input field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alex.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlexDialog(response, alexImage)
        );
        userInput.clear();
    }
}
