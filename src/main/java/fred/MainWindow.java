package fred;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Fred fred;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image fredImage = new Image(this.getClass().getResourceAsStream("/images/DaFred.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getFredDialog("Hello! I'm Fred\nWhat can I do for you?", fredImage)
        );
    }

    /** Injects the Fred instance */
    public void setFred(Fred fred) {
        this.fred = fred;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Fred's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fred.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFredDialog(response, fredImage)
        );
        userInput.clear();
        if (!fred.getIsRunning()) {
            fred.exit();
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();
        }
    }
}
