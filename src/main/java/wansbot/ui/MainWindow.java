package wansbot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wansbot.WansBot;

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

    private WansBot wansBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/daUser.png"));
    private Image wansImage = new Image(this.getClass().getResourceAsStream("/images/daWansBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the WansBot instance */
    public void setWansBot(WansBot w) {
        wansBot = w;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(wansBot.getResponse("hello"), wansImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wansBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, wansImage)
        );
        if (input.equals("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }
}