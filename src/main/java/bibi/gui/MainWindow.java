package bibi.gui;

import bibi.Bibi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    public static ErrorMessage errorMessage;

    @FXML
    private Label errorLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bibi bibi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PFP1.gif"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.jfif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        errorMessage = new ErrorMessage(errorLabel);
    }

    /** Injects the Duke instance */
    public void setDuke(Bibi bibi) {
        this.bibi = bibi;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bibi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userImage != null : "Should not have empty user picture";
        assert botImage != null : "Should not have empty bot picture";

        String input = userInput.getText();
        String response = bibi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(input, userImage),
                DialogBox.getBotDialogBox(response, botImage)
        );
        userInput.clear();
    }
}
