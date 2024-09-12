import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Alex instance and displays the welcome message.
     */
    public void setAlex(Alex alex) {
        this.alex = alex;
        displayWelcomeMessage();  // Display the welcome message upon initialization
    }

    /**
     * Displays the welcome message from Alex in the dialog container.
     */
    private void displayWelcomeMessage() {
        String welcomeMessage = alex.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getAlexDialog(welcomeMessage, alexImage));
    }

    /**
     * Handles user input and updates the GUI with Alex's response.
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
