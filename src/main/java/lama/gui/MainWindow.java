package lama.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lama.Lama;


/**
 * The MainWindow class serves as the controller for the main GUI of the application.
 * It manages the interaction between the user input and the application's response, displaying
 * dialog boxes in a scrollable container.
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

    private Lama lama;

    private Image userImage;
    private Image lamaImage;

    /**
     * Initializes the MainWindow by binding the vertical scroll value of the scroll pane
     * to the height property of the dialog container, ensuring the latest dialog is always visible.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initializeImages();
        addInitialLamaDialog();
    }

    private void initializeImages() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
        lamaImage = new Image(this.getClass().getResourceAsStream("/images/lama.png"));
    }

    private void addInitialLamaDialog() {
        DialogBox dialogBox = DialogBox.getLamaDialog("Hello! I'm Lama\nWhat can I do for you?", lamaImage);
        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Injects the Lama instance into this controller.
     * This method allows the MainWindow to communicate with the backend logic of the application.
     *
     * @param lama The Lama instance to be injected
     */
    public void setLama(Lama lama) {
        this.lama = lama;
    }

    /**
     * Handles the user input by creating two dialog boxes:
     * one that echoes the user's input and another that displays Lama's response.
     * These dialog boxes are then appended to the dialogContainer.
     * The user input field is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lama.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLamaDialog(response, lamaImage)
        );
        userInput.clear();
    }
}
