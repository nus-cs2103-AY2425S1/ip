package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;
import tako.Tako;
import ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image takoImage = new Image(this.getClass().getResourceAsStream("/images/Tako.png"));

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Tako tako;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getTakoMessage(Ui.greet(), takoImage)
        );
    }

    /** Injects the Duke instance */
    public void setTako(Tako tako) {
        this.tako = tako;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String takoResponse = Parser.parse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserMessage(userText, userImage),
                DialogBox.getTakoMessage(takoResponse, takoImage)
        );
        userInput.clear();
        if (userText.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
