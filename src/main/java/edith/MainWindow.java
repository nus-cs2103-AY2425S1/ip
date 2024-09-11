package edith;

import edith.guicontrol.DialogBox;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Edith edith;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image EdithImage = new Image(this.getClass().getResourceAsStream("/images/DaEdith.png"));

    @FXML
    public void initialize() {
        // Scroll scrollPane down to the end every time dialogContainer's height changes
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Edith instance */
    public void setEdith(Edith e) {
        edith = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Edith's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String edithText = edith.reply(userText);

        DialogBox userBox = DialogBox.getUserDialog(userText, userImage);
        DialogBox edithBox = DialogBox.getEdithDialog(edithText, EdithImage);

        dialogContainer.getChildren().addAll(userBox, edithBox);
        userInput.clear();
    }
}
