package ekud.ui;

import ekud.Ekud;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 *
 * @author uniqly
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
    @FXML
    private Scene scene;
    private Ekud ekud;
    private Image ekudImage = new Image(this.getClass().getResourceAsStream("/images/upside-down.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/flushed.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@link Ekud} instance.
     *
     * @param e The injected {@link Ekud} instance.
     */
    public void setEkud(Ekud e) {
        ekud = e;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getEkudDialog("ha: " + userText, ekudImage)
        );
        userInput.clear();
    }
}
