package ava.gui;

import ava.AVA;
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

    private AVA ava;

    private AssetManager assetManager;
    private Image userImage;
    private Image avaImage;

    private void welcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getAVADialog(ava.welcomeUser(), avaImage));
    }

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        assetManager = new AssetManager();
        userImage = assetManager.getUserImage();
        avaImage = assetManager.getAVAImage();
    }

    //CHECKSTYLE.OFF: AbbreviationAsWordInName
    /**
     * Injects the AVA instance.
     */
    public void setAVA(AVA ava) {
        this.ava = ava;
        welcomeMessage();
    }
    //CHECKSTYLE.ON: AbbreviationAsWordInName

    /**
     * Creates two dialog boxes, one echoing user input and the other containing AVA's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        ava.tellAva(input);
        String response = ava.respond();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAVADialog(response, avaImage)
        );
        userInput.clear();
    }
}
