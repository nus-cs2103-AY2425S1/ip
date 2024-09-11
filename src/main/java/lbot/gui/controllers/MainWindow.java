package lbot.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lbot.LBot;

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

    private LBot lbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lbotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     */
    public void setLBot(LBot l) {
        lbot = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "You did not enter anything!";
        String response = lbot.getResponse(input);
        assert response != null : "Response was empty.";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLBotDialog(response, lbotImage)
        );
        if (response.equals("Bye-bi!!")) {
            Platform.exit();
        }
        userInput.clear();
    }

    /**
     * Sets up bot's greeting when application is first opened.
     */
    public void greeting() {
        String greeting = lbot.getGreeting();
        dialogContainer.getChildren().add(DialogBox.getLBotDialog(greeting, lbotImage));
    }
}


