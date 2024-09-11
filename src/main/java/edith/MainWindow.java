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
 * Manages user input and displays Edith's responses in the form of dialog boxes.
 */
public class MainWindow extends AnchorPane {
    /** Scroll pane to display the list of dialog boxes. */
    @FXML
    private ScrollPane scrollPane;

    /** Container for dialog boxes (user input and Edith responses). */
    @FXML
    private VBox dialogContainer;

    /** Text field for user input. */
    @FXML
    private TextField userInput;

    /** Button to send the user input. */
    @FXML
    private Button sendButton;

    /** The Edith chatbot instance for processing user input. */
    private Edith edith;

    /** The profile images displayed in dialog boxes for the user and edith respectively. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image EdithImage = new Image(this.getClass().getResourceAsStream("/images/DaEdith.png"));

    /**
     * Initializes the GUI components.
     * Binds the scroll pane to automatically scroll down when new dialog boxes are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Edith instance into the controller.
     * This method allows the Edith instance to be set after the MainWindow is loaded.
     *
     * @param e The Edith instance.
     */
    public void setEdith(Edith e) {
        edith = e;
    }

    /**
     * Handles user input by creating two dialog boxes, one for the user input
     * and the other for Edith's reply. They are then appended to the dialog container.
     * The user input field is cleared after processing.
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
