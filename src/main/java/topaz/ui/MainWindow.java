package topaz.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import topaz.main.Topaz;

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

    private Topaz topaz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image topazImage = new Image(this.getClass().getResourceAsStream("/images/DaTopaz.png"));

    /**
     * Automatically called when load the FXML file, adds welcome message in the beginning.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = new Ui().welcome();
        dialogContainer.getChildren().add(DialogBox.getTopazDialog(welcome, topazImage, ""));
    }

    /** Injects the Duke instance */
    public void setTopaz(Topaz d) {
        topaz = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = topaz.getResponse(input);
        String commandType = topaz.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTopazDialog(response, topazImage, commandType)
        );
        userInput.clear();
    }
}
