package com.nimbus;

import java.util.Objects;

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

    private Image userImage;
    private Image botImage;
    private Nimbus bot;

    /**
     * Initialize MainWindow
     * @param userImagePath file path of user icon
     * @param botImagePath file path of bot icon
     */
    @FXML
    public void initialize(String userImagePath, String botImagePath) {
        this.userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(userImagePath)));
        this.botImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(botImagePath)));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNimbus(Nimbus bot) {
        this.bot = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert this.bot != null : "bot should be non-null";
        this.bot.executeCommand(userInput.getText());
        userInput.clear();
    }

    private void showMessage(String message, boolean fromUser) {
        if (fromUser) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDialog(message, userImage, false)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDialog(message, botImage, true)
            );
        }
    }

    public void showBotMessage(String message) {
        showMessage(message, false);
    }

    public void showUserMessage(String message) {
        showMessage(message, true);
    }
}
