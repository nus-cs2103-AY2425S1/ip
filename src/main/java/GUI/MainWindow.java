package gui;

import javafx.application.Platform;
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

    private Duch duch;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    /**
     * Automatically called. Ensures scroll pane scrolls to bottom when new messages are added.
     * 
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Outputs the welcome message.
     */
    public void welcome() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDuchDialog(duch.getWelcomeString(), dukeImage)
        );
    }

    /** 
     * Injects the Duke instance.
     */
    public void setDuch(Duch duch) {
        this.duch = duch;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            duch.save();
            Platform.exit();
        } else if (input.equals("")) {
           return;
        }
        
        String response = duch.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuchDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
