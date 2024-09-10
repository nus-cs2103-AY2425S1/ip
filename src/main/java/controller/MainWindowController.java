package controller;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tanjirobot.Tanjiro;


/**
 * Controller for the main GUI.
 */
public class MainWindowController extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Tanjiro tanjiro;

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/nezuko.png"));
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final Image TANJIRO_IMAGE = new Image(this.getClass().getResourceAsStream("/images/tanjiro.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setTanjiro(Tanjiro t) {
        tanjiro = t;
        greetMessage();
    }

    @FXML
    private void greetMessage() {
        String response = tanjiro.onStartup();
        dialogContainer.getChildren().addAll(
                DialogBoxController.getTanjiroDialog(response, TANJIRO_IMAGE)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = tanjiro.run(input);
        dialogContainer.getChildren().addAll(
                DialogBoxController.getUserDialog(input, USER_IMAGE),
                DialogBoxController.getTanjiroDialog(response, TANJIRO_IMAGE)
        );
        userInput.clear();
    }
}
