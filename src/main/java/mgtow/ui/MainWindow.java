package mgtow.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mgtow.Mgtow;

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

    private Mgtow mgtow;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_avatar.png"));
    private Image mgtowImage = new Image(this.getClass().getResourceAsStream("/images/mgtow_avatar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mgtow instance */
    public void setMgtow(Mgtow m) {
        mgtow = m;
        String welcomeMessage = mgtow.getUi().getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getMgtowDialog(welcomeMessage, mgtowImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mgtow.processCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMgtowDialog(response, mgtowImage)
        );
        userInput.clear();
        if (mgtow.shouldExit(input)) {
            Platform.exit();
        }
    }
}