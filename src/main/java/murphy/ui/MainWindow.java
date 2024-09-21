package murphy.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import murphy.Murphy;
import murphy.MurphyException;
import murphy.ui.DialogBox;

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

    private Murphy murphy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image murphyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setMurphy(Murphy murphy) {
        this.murphy = murphy;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        murphy.inputListener(input);
        userInput.clear();
    }

    public void showText(String text) {
        dialogContainer.getChildren().addAll(
                DialogBox.getMurphyDialogue(text, murphyImage)
        );
    }

    public void showError(String error) {
        dialogContainer.getChildren().addAll(
                DialogBox.getMurphyDialogue("ERROR: " + error, murphyImage)
        );
    }
}
