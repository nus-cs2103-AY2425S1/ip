import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ontos.Ontos;
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

    private Ontos ontos;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image ontosImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.jpg"));
    private boolean isFirstInteraction = true;

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        askForSaveLocation();
    }

    /** Injects the Ontos instance */
    public void setOntos(String saveLocation) {
        ontos = new Ontos(saveLocation);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;

        if (isFirstInteraction) {
            setOntos(input);
            response = " Save location set to: " + input + ".txt" + "\n" + ontos.getGreeting();
            isFirstInteraction = false;
        } else {
            response = ontos.getResponse(input);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOntosDialog(response, ontosImage)
        );
        userInput.clear();
    }

    /**
     * Asks for a save location from the user when the chatbot is launched.
     */
    @FXML
    private void askForSaveLocation() {
        dialogContainer.getChildren().add(DialogBox.getOntosDialog(" Please enter a save location.", ontosImage));
    }
}
