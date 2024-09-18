package yapmeister.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yapmeister.YapMeister;

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

    private YapMeister yapMeister;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/fish-sprite.png"));
    private Image yapMeisterImage = new Image(this.getClass().getResourceAsStream("/images/yapmeister.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the YapMeister instance */
    public void setYapMeister(YapMeister d) {
        yapMeister = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getYapMeisterDialog(yapMeister.getWelcomeMessage(), yapMeisterImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing YapMeister's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yapMeister.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapMeisterDialog(response, yapMeisterImage)
        );
        userInput.clear();
    }
}
