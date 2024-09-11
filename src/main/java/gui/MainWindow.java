package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import windebot.Winde;

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

    private Winde winde;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image windeImage = new Image(this.getClass().getResourceAsStream("/images/winde.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Winde instance */
    public void setWinde(Winde winde) {
        this.winde = winde;
        dialogContainer.getChildren().addAll(
                DialogBox.getWindeDialog(winde.hello(), windeImage, ""));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = winde.getResponse(input);
        String commandType = winde.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWindeDialog(response, windeImage, commandType)
        );
        userInput.clear();
        /*
        if (!winde.getWillContinue()) {
            Main.stop();
        }
         */
    }
}
