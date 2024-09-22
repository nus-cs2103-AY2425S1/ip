package drbrown.gui;

import drbrown.DrBrown;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * This class manages the layout and interactions of the main window in the DrBrown application.
 * It handles user input, displays responses from the DrBrown instance, and updates the GUI elements.
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

    private DrBrown drBrown;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/MartyRemoveBg.png"));
    private final Image drBrownImage = new Image(this.getClass().getResourceAsStream("/images/DrBrownRemoveBg.png"));

    /**
     * Initializes the MainWindow controller.
     * Binds the vertical scroll value of the scrollPane to the height of the dialogContainer
     * to ensure that the most recent dialog is always visible.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the DrBrown instance and sets up the initial greeting dialog.
     * This method is called by the main application class after the FXML has been loaded.
     *
     * @param drBrown The DrBrown instance to be used for generating responses.
     */
    public void setDrBrown(DrBrown drBrown) {
        assert drBrown != null : "DrBrown should not be null";
        this.drBrown = drBrown;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(drBrown.showGreeting(), drBrownImage)
        );
    }

    /**
     * Handles user input by creating two dialog boxes: one echoing the user's input and
     * the other containing DrBrown's response. It then appends these dialogs to the dialog container
     * and clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(drBrown.showOutput(input), drBrownImage)
        );
        userInput.clear();
    }
}
