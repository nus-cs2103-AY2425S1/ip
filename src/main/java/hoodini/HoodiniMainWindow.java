package hoodini;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to handle main window of the application
 */

public class HoodiniMainWindow extends Stage {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Stage stage = new Stage();

    private Hoodini hoodini;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Pikachu.png"));
    private Image hoodiniImage = new Image(this.getClass().getResourceAsStream("/images/Mochi.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                HoodiniDialogBox.getHoodiniDialog("Hello! I am Hoodini! How may I assist you?", hoodiniImage)
        );
    }

    /** Injects the Duke instance */
    public void setHoodini(Hoodini hoodini) {
        this.hoodini = hoodini;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hoodini.handleInput(input);
        dialogContainer.getChildren().addAll(
                HoodiniDialogBox.getUserDialog(input, userImage),
                HoodiniDialogBox.getHoodiniDialog(response, hoodiniImage)
        );
        userInput.clear();
    }



}
