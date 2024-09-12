import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import killua.Killua;

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

    private Killua killua;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/gon.jpg"));
    private final Image killuaImage = new Image(this.getClass().getResourceAsStream("/images/killua.jpg"));

    /** Initialize main window */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane is not initialized!";
        assert dialogContainer != null : "DialogContainer is not initialized!";
        assert userInput != null : "UserInput is not initialized!";
        assert sendButton != null : "SendButton is not initialized!";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Killua instance */
    public void setKillua(Killua k) {
        assert k != null : "Killua instance cannot be null!";
        killua = k;
        welcomeUser();
    }

    /** Show welcome message */
    @FXML
    public void welcomeUser() {
        assert killua != null : "Killua instance should be set before welcoming user!";
        assert killuaImage != null : "Killua image is not loaded!";
        assert userImage != null : "User image is not loaded!";
        dialogContainer.getChildren().add(
                DialogBox.getKilluaDialog(killua.welcomeUser(), killuaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.trim().isEmpty() : "User input should not be empty!";
        String response = killua.getResponse(input);
        assert response != null : "Response from Killua should not be null!";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKilluaDialog(response, killuaImage)
        );
        userInput.clear();
    }
}
